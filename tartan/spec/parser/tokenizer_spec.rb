require 'spec_helper'

RSpec::Matchers.define :be_token_of do |expected|
  match do |actual|
    actual.type == expected.type
    actual.value == expected.value
    actual.linum == expected.linum
  end
end

module Parser
  describe Tokenizer do
    context "single line of declaration" do
      it "parses line only with State name" do
        tokenizer = Tokenizer.new(StringIO.new("SomeState"))
        match_tokens(tokenizer, [
                       Token.new(:INDENT, 0, 1),
                       Token.new(:ID, "SomeState", 1),
                       Token.new(:EOL, nil, 1)
                     ])
      end

      it "parses line with indent" do
        tokenizer = Tokenizer.new(StringIO.new("  SomeState"))
        match_tokens(tokenizer, [
                       Token.new(:INDENT, 2, 1),
                       Token.new(:ID, "SomeState", 1),
                       Token.new(:EOL, nil, 1)
                     ])
      end

      it "parses line with variable name and or-states" do
        tokenizer = Tokenizer.new(StringIO.new("variable : A, B, C"))
        match_tokens(tokenizer, [
                       Token.new(:INDENT, 0, 1),
                       Token.new(:VARIABLE, "variable", 1),
                       Token.new(:ASSIGNMENT, ASSIGNMENT, 1),
                       Token.new(:ID, "A", 1),
                       Token.new(:STATE_SEPARATOR, STATE_SEPARATOR, 1),
                       Token.new(:ID, "B", 1),
                       Token.new(:STATE_SEPARATOR, STATE_SEPARATOR, 1),
                       Token.new(:ID, "C", 1),
                       Token.new(:EOF, nil, 1)
                     ])
      end

      it "parses case declaration" do
        tokenizer = Tokenizer.new(StringIO.new("case A"))
        match_tokens(tokenizer, [
                       Token.new(:INDENT, 0, 1),
                       Token.new(:CASE_DECL, CASE_DECL, 1),
                       Token.new(:ID, "A", 1),
                       Token.new(:EOL, nil, 1)
                     ])
      end

      it "parses state with primary state declaration " do
        tokenizer = Tokenizer.new(StringIO.new("variable : union A, B"))
        match_tokens(tokenizer, [
                       Token.new(:INDENT, 0, 1),
                       Token.new(:VARIABLE, "variable", 1),
                       Token.new(:ASSIGNMENT, ASSIGNMENT, 1),
                       Token.new(:PRIMARY_STATE_DECL, PRIMARY_STATE_DECL, 1),
                       Token.new(:ID, "A", 1),
                       Token.new(:STATE_SEPARATOR, STATE_SEPARATOR, 1),
                       Token.new(:ID, "B", 1),
                       Token.new(:EOL, nil, 1)
                     ])
      end

      it "parses state with Plaid literal" do
        tokenizer = Tokenizer.new(StringIO.new("variable : A { literal }"))
        match_tokens(tokenizer, [
                       Token.new(:INDENT, 0, 1),
                       Token.new(:VARIABLE, "variable", 1),
                       Token.new(:ASSIGNMENT, ASSIGNMENT, 1),
                       Token.new(:ID, "A", 1),
                       Token.new(:PLAID_LITERAL, "{ literal }", 1),
                       Token.new(:EOL, nil, 1)
                     ])
      end

      it "parses multiple states with Plaid literals" do
        tokenizer = Tokenizer.new(StringIO.new("variable : A { literal }, B, C { literal }"))
        match_tokens(tokenizer, [
                       Token.new(:INDENT, 0, 1),
                       Token.new(:VARIABLE, "variable", 1),
                       Token.new(:ASSIGNMENT, ASSIGNMENT, 1),
                       Token.new(:ID, "A", 1),
                       Token.new(:PLAID_LITERAL, "{ literal }", 1),
                       Token.new(:STATE_SEPARATOR, STATE_SEPARATOR, 1),
                       Token.new(:ID, "B", 1),
                       Token.new(:STATE_SEPARATOR, STATE_SEPARATOR, 1),
                       Token.new(:ID, "C", 1),
                       Token.new(:PLAID_LITERAL, "{ literal }", 1),
                       Token.new(:EOL, nil, 1)
                     ])
      end

      it "parses state with Plaid literal which includes curly brackets" do
        tokenizer = Tokenizer.new(StringIO.new("variable : A { literal {} }"))
        match_tokens(tokenizer, [
                       Token.new(:INDENT, 0, 1),
                       Token.new(:VARIABLE, "variable", 1),
                       Token.new(:ASSIGNMENT, ASSIGNMENT, 1),
                       Token.new(:ID, "A", 1),
                       Token.new(:PLAID_LITERAL, "{ literal {} }", 1),
                       Token.new(:EOL, nil, 1)
                     ])
      end

      it "parses state with Plaid literal which includes unmatched open curly bracket inside of string literal " do
        tokenizer = Tokenizer.new(StringIO.new(%Q!variable : A { "{" literal }!))
        match_tokens(tokenizer, [
                       Token.new(:INDENT, 0, 1),
                       Token.new(:VARIABLE, "variable", 1),
                       Token.new(:ASSIGNMENT, ASSIGNMENT, 1),
                       Token.new(:ID, "A", 1),
                       Token.new(:PLAID_LITERAL, %Q!{ "{" literal }!, 1),
                       Token.new(:EOL, nil, 1)
                     ])
      end

      it "parses state with Plaid literal which includes unmatched close curly bracket inside of string literal " do
        tokenizer = Tokenizer.new(StringIO.new(%Q!variable : A { "}" literal }!))
        match_tokens(tokenizer, [
                       Token.new(:INDENT, 0, 1),
                       Token.new(:VARIABLE, "variable", 1),
                       Token.new(:ASSIGNMENT, ASSIGNMENT, 1),
                       Token.new(:ID, "A", 1),
                       Token.new(:PLAID_LITERAL, %Q!{ "}" literal }!, 1),
                       Token.new(:EOL, nil, 1)
                     ])
      end
    end

    context "multiple lines of declarations" do
      it "parses multiple lines of state declarations" do
        tokenizer = Tokenizer.new(StringIO.new("parent : P1, P2\n  child : C1, C2"))
        match_tokens(tokenizer, [
                       Token.new(:INDENT, 0, 1),
                       Token.new(:VARIABLE, "parent", 1),
                       Token.new(:ASSIGNMENT, ASSIGNMENT, 1),
                       Token.new(:ID, "P1", 1),
                       Token.new(:STATE_SEPARATOR, STATE_SEPARATOR, 1),
                       Token.new(:ID, "P2", 1),
                       Token.new(:EOF, nil, 1),
                       Token.new(:INDENT, 2, 2),
                       Token.new(:VARIABLE, "child", 2),
                       Token.new(:ASSIGNMENT, ASSIGNMENT, 2),
                       Token.new(:ID, "C1", 2),
                       Token.new(:STATE_SEPARATOR, STATE_SEPARATOR, 2),
                       Token.new(:ID, "C2", 2),
                       Token.new(:EOF, nil, 2),
                     ])
      end

      it "parses multiple lines of Plaid literal as a single token" do
        tokenizer = Tokenizer.new(StringIO.new("parent : P1 { first line\n  second line}, P2"))
        match_tokens(tokenizer, [
                       Token.new(:INDENT, 0, 1),
                       Token.new(:VARIABLE, "parent", 1),
                       Token.new(:ASSIGNMENT, ASSIGNMENT, 1),
                       Token.new(:ID, "P1", 1),
                       Token.new(:PLAID_LITERAL, "{ first line\n  second line}", 1),
                       Token.new(:STATE_SEPARATOR, STATE_SEPARATOR, 2),
                       Token.new(:ID, "P2", 2),
                       Token.new(:EOF, nil, 2)
                     ])
      end
    end
  end
end

def match_tokens(tokenizer, tokens)
  tokens.each do |token|
    tokenizer.next_token.should be_token_of token
  end
end
