module Codegenerator
  module Plaid
    class Structure
      include Codegenerator::Plaid

      def initialize(ast)
        @root = ast.root
        @app = ast.app
        @case_containing = []
        @output = {}
      end

      def generate(output_dir)
        generate_state_decl
        generate_case_states
        generate_main

        generate_source_files(output_dir)
      end

      def set_current(decl)
        state_name = decl.id
        @current = @output[state_name] = {}
        @current[:header] = []
        @current[:decl] = []
        @current[:init] = []
        @current[:footer] = []
      end

      def generate_state_decl
        set_current(@root)
        @root.accept(self)
      end

      def generate_case_states
        @case_containing.each do |decl|
          set_current(decl)
          @current[:header] << "state #{decl.primary_state.id}"

          @current[:header].last << " {"
          @current[:decl] << "method on#{decl.primary_state.id}(ctx) {"
          @current[:decl] << "  ctx <- #{decl.children.first.id};"
          @current[:decl] << "}"
          @current[:footer] << "}"
          decl.children.each do |c|
            c.accept(self)
          end
        end
      end

      def generate_main
        @output[:main] = []
        @output[:main] << "method main() {"
        @output[:main] << "  var body = new Body { id = \"body\"; } with #{@root.id};"
        @output[:main] << "  body.init(body);"
        @output[:main] << "}"
      end

      def generate_source_files(output_dir)
        open("#{output_dir}/#{@root.id}.plaid", "w") do |f|
          #TODO package should be generated automaticaly
          f.puts "package tartan.structure;"
          f.puts
          @output.each do |k, v|
            next if k == :main
            [:header, :decl, :init, :footer].each do |w|
              next if @output[k][w].size == 0
              if w == :init
                f.puts "method init(parent){"
              end
              f.puts @output[k][w].join("\n")
              f.puts "}" if w == :init

              f.puts
            end
            f.puts
          end
          f.puts @output[:main].join("\n") if @app
        end
      end

      def visit_state_def(state_def)
        @current[:header] << "state #{state_def.id}"
        if state_def.literal
          @current[:header].last << " = #{state_def.literal}"
          @current[:header].last << and_states_literal(state_def, " with ")
        else
          @current[:header].last << " = {"
          @current[:decl] << "  method on#{state_def.id}(ctx){"

          @current[:decl] << and_states_literal(state_def, "    ctx <- ", ";")

          @current[:decl] << "    ctx <- #{state_def.children.first.id};" if state_def.children.first.class == Parser::AST::CaseDecl

          @current[:decl] << "  }"
          @current[:footer] << "}"


          state_def.children.each{|c| c.accept(self)}
        end
      end

      def visit_variable_decl(decl)
        if(decl.id.size > 0)
          @current[:decl] << "var #{decl.id};"
          @current[:init] << "this.#{decl.id} = new " + and_states_literal(decl) + ";"
          if decl.parent_node && decl.parent_node.class == Parser::AST::Decl
            @current[:init] << "this.#{decl.id}.setParent(this.#{decl.parent_node.id});"
          else
            @current[:init] << "this.#{decl.id}.setParent(parent);"
          end
          @current[:init] << "this.#{decl.id}.root = this;";
        else
          if decl.parent_node  &&  decl.parent_node.class == Parser::AST::Decl
            @current[:init] << "(new #{decl.primary_state.to_state_literal}).setParent(this.#{decl.parent_node.id});"
          else
            @current[:init] << "(new #{decl.primary_state.to_state_literal}).setParent(parent);"
          end
        end

        if(decl.children.first.class == Parser::AST::CaseDecl)
          @case_containing.push(decl)
        else
          decl.children.each{|c| c.accept(self) }
        end
      end

      def visit_case_decl(decl)
        set_current(decl)

        @current[:header] << "state #{decl.id} case of #{decl.parent_node.state_id}"
        @current[:header].last << " = {"
        @current[:header] << "method on#{decl.id}(ctx){ ctx.init(ctx); }"
        @current[:header] << "method off#{decl.id}(ctx){ ctx.clear(); }"

        @current[:footer] << "}"
        @current[:footer].last << and_states_literal(decl, " with ")

        decl.children.each do |c|
          c.accept(self)
        end
      end
    end
  end
end
