module Codegenerator
  module Plaid
    class Style
      include Codegenerator::Plaid

      def initialize(ast)
        @root = ast.root
        @output = {}
      end

      def generate(output_dir)
        @current = setup_current(@root)
        @root.accept(self)

        generate_source_files(output_dir)
      end

      def setup_current(decl)
        out = @output[decl.id] = {}

        out[:header] = []
        out[:decl] = []
        out[:footer] = []

        return out
      end

      def generate_source_files(output_dir)
        open("#{output_dir}/#{@root.id}.plaid", "w") do |f|
          #TODO package should be generated automaticaly
          f.puts "package tartan.style;"
          f.puts
          @output.each do |k, v|
            [:header, :decl, :footer].each do |w|
              next if @output[k][w].size == 0
              f.puts @output[k][w].join("\n")
              f.puts
            end
          end
        end
      end

      def visit_state_def(state_def)
        @current[:header] << "state #{state_def.id} "
        @current[:header].last << " = {"

        @current[:decl] << "  method on#{state_def.id}(ctx){"
        @current[:decl] << and_states_literal(state_def, "    ctx <- ", ";")
        @current[:decl] << "  }"
        state_def.children.each{|c| c.accept(self)}

        @current[:footer] << "}"
      end

      def visit_case_decl(case_decl)
        state_name = "#{case_decl.id}#{case_decl.parent_node.id}"
        @current[:decl] << "method on#{case_decl.id}(ctx) { ctx <- #{state_name}; }"

        out = setup_current(case_decl)
        out[:header] << "state #{state_name} case of #{case_decl.parent_node.id}"
        out[:header].last << and_states_literal(case_decl, " = ")
        # out[:decl] << and_states_literal(case_decl, "method on#{case_decl.id}(ctx) { ctx <- ", "; }")
        # out[:footer] << "}"
      end
    end
  end
end
