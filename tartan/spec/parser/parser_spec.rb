require 'spec_helper'

RSpec::Matchers.define :be_state_definition_of do |expected|
  match do |actual|
    actual.linum.should == expected.linum
    actual.id.should == expected.id
    actual.literal.should == expected.literal
    actual.and_states.zip(expected.and_states).each do |pair|
      pair[0].should be_state_definition_of pair[1]
    end
    actual.parent.should == expected.parent
  end
end

RSpec::Matchers.define :be_variable_declaration_of do |expected|
  match do |actual|
    actual.linum.should == expected.linum
    actual.indent_level.should == expected.indent_level
    actual.id.should == expected.id
    actual.and_states.zip(expected.and_states).each do |pair|
      pair[0].should be_state_definition_of pair[1]
    end
    actual.primary_state.should be_state_definition_of expected.primary_state
  end
end

RSpec::Matchers.define :be_case_declaration_of do |expected|
  match do |actual|
    actual.linum.should == expected.linum
    actual.indent_level.should == expected.indent_level
    actual.id.should == expected.id
    actual.literal.should == expected.literal
    actual.and_states.zip(expected.and_states).each do |pair|
      pair[0].should be_state_definition_of pair[1]
    end
  end
end


module Parser
  describe Parser do
    context "state definition" do
      it "should generate StateDef" do
        ast = generate_ast("SomeState")
        ast.should be_state_definition_of AST::StateDef.new(1, "SomeState")
      end

      it "should generate StateDef with Plaid literal" do
        ast = generate_ast("SomeState { foo = bar; }")
        ast.should be_state_definition_of AST::StateDef.new(1, "SomeState", "{ foo = bar; }")
      end

      it "should generate StateDef with and_states" do
        ast = generate_ast("A { foo = bar; }, B { foo = bar; }, C { foo = bar; }")
        ast.should be_state_definition_of AST::StateDef.new(1, "A", "{ foo = bar; }", [
                                                              AST::StateDef.new(1, "B", "{ foo = bar; }"),
                                                              AST::StateDef.new(1, "C", "{ foo = bar; }")
                                                            ])
      end

      it "should generate StateDef with and_states in multiple lines" do
        ast = generate_ast("A { foo = bar; }, B { foo = bar; },\n C { foo = bar; }")
        ast.should be_state_definition_of AST::StateDef.new(1, "A", "{ foo = bar; }", [
                                                              AST::StateDef.new(1, "B", "{ foo = bar; }"),
                                                              AST::StateDef.new(2, "C", "{ foo = bar; }")
                                                            ])
      end
    end
    context "variable declaration" do
      it "should generate variable declaration" do
        ast = generate_ast("A\n  b: B\n  c: C")

        a = AST::StateDef.new(1, "A")
        state_b = AST::StateDef.new(2, "B")
        state_c = AST::StateDef.new(3, "C")
        b = AST::Decl.new(2, 2, "b", [state_b], state_b)
        c = AST::Decl.new(3, 2, "c", [state_c], state_c)
        a.add_child(b)
        a.add_child(c)

        ast.should be_state_definition_of a
        ast.children.zip([b, c]).each do |pair|
          pair[0].should be_variable_declaration_of pair[1]
        end
      end

      it "should generate variable declaration with Plaid literals" do
        ast = generate_ast("A{a}\n  b: B{b}\n  c: C{c}")

        a = AST::StateDef.new(1, "A", "{a}")
        state_b = AST::StateDef.new(2, "B", "{b}")
        state_c = AST::StateDef.new(3, "C", "{c}")
        b = AST::Decl.new(2, 2, "b", [state_b], state_b)
        c = AST::Decl.new(3, 2, "c", [state_c], state_c)
        a.add_child(b)
        a.add_child(c)

        ast.should be_state_definition_of a
        ast.children.zip([b, c]).each do |pair|
          pair[0].should be_variable_declaration_of pair[1]
        end
      end

      it "should generate variable declaration with and_states" do
        ast = generate_ast("A{a}\n  b: B{b}, C{c}, D")

        a = AST::StateDef.new(1, "A", "{a}")
        state_b = AST::StateDef.new(2, "B", "{b}")
        state_c = AST::StateDef.new(2, "C", "{c}")
        state_d = AST::StateDef.new(2, "D")
        b = AST::Decl.new(2, 2, "b", [state_b, state_c, state_d], state_d)
        a.add_child(b)

        ast.should be_state_definition_of a
        ast.children.zip([b]).each do |pair|
          pair[0].should be_variable_declaration_of pair[1]
        end
      end

      it "should generate variable declaration with and_states in multiple lines" do
        ast = generate_ast("A{a}\n  b: B{b}, C{c},\n D")

        a = AST::StateDef.new(1, "A", "{a}")
        state_b = AST::StateDef.new(2, "B", "{b}")
        state_c = AST::StateDef.new(2, "C", "{c}")
        state_d = AST::StateDef.new(3, "D")
        b = AST::Decl.new(2, 2, "b", [state_b, state_c, state_d], state_d)
        a.add_child(b)

        ast.should be_state_definition_of a
        ast.children.zip([b]).each do |pair|
          pair[0].should be_variable_declaration_of pair[1]
        end
      end


      it "should generate variable declaration with primary_state" do
        ast = generate_ast("A{a}\n  b: union B{b}, C{c}, D")

        a = AST::StateDef.new(1, "A", "{a}")
        state_b = AST::StateDef.new(2, "B", "{b}")
        state_c = AST::StateDef.new(2, "C", "{c}")
        state_d = AST::StateDef.new(2, "D")
        b = AST::Decl.new(2, 2, "b", [state_b, state_c, state_d], state_b)
        a.add_child(b)

        ast.should be_state_definition_of a
        ast.children.zip([b]).each do |pair|
          pair[0].should be_variable_declaration_of pair[1]
        end
      end
    end

    context "case declaration" do
      it "should generate case declaration" do
        ast = generate_ast("A\n  case Case1\n    a1: A1\n  case Case2\n    a2:A2")

        a = AST::StateDef.new(1, "A")
        case1 = AST::CaseDecl.new(2, 2, "Case1")
        case2 = AST::CaseDecl.new(4, 2, "Case2")

        state_a1 = AST::StateDef.new(3, "A1")
        state_a2 = AST::StateDef.new(5, "A2")
        a1 = AST::Decl.new(3, 4, "a1", [state_a1], state_a1)
        a2 = AST::Decl.new(5, 4, "a2", [state_a2], state_a2)
        a.add_child(case1)
        a.add_child(case2)
        case1.add_child(a1)
        case2.add_child(a2)

        ast.should be_state_definition_of a
        ast.children.zip([case1, case2]).each do |pair|
          pair[0].should be_case_declaration_of pair[1]
        end
        ast.children.first.children.first.should be_variable_declaration_of a1
        ast.children.last.children.first.should be_variable_declaration_of a2
      end

      it "should generate case declaration with Plaid literals" do
        ast = generate_ast("A\n  case Case1 {case1}\n    a1: A1{a1}")

        a = AST::StateDef.new(1, "A")
        case1 = AST::CaseDecl.new(2, 2, "Case1", "{case1}")
        state_a1 = AST::StateDef.new(3, "A1", "{a1}")
        a1 = AST::Decl.new(3, 4, "a1", [state_a1], state_a1)
        a.add_child(case1)
        case1.add_child(a1)

        ast.should be_state_definition_of a
        ast.children.first.should be_case_declaration_of case1
        ast.children.first.children.first.should be_variable_declaration_of a1
      end

      it "should generate case declaration with and_states" do
        ast = generate_ast("A\n  case Case1, B, C\n    a1: A1")

        a = AST::StateDef.new(1, "A")
        state_b = AST::StateDef.new(2, "B")
        state_c = AST::StateDef.new(2, "C")
        state_a1 = AST::StateDef.new(3, "A1")
        a1 = AST::Decl.new(3, 4, "a1", [state_a1], state_a1)

        case1 = AST::CaseDecl.new(2, 2, "Case1")
        case1.and_states = [state_b, state_c]

        a.add_child(case1)
        case1.add_child(a1)

        ast.should be_state_definition_of a
        ast.children.first.should be_case_declaration_of case1
        ast.children.first.children.first.should be_variable_declaration_of a1
      end

    end
  end
end

def generate_ast(str)
  ast = Parser::Parser.new(Parser::Tokenizer.new(StringIO.new(str))).parse
  ast.root
end

