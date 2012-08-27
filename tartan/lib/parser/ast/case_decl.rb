module Parser
  module AST
    class CaseDecl
      attr_accessor :linum, :id, :indent_level, :literal, :and_states, :parent_node, :children
      def initialize(linum, indent_level, id, literal = nil, and_states = [])
        @linum = linum
        @id = id
        @indent_level = indent_level
        @literal = literal
        @and_states = and_states

        @parent_node = nil
        @children = []
      end

      def add_child(child)
        @children.push(child)
        child.parent_node = self
      end

      def state_id
        @id
      end

      def to_s
        puts "-- case decl --"
        puts "id : #{@id}"
        puts "literal : #{@literal}"
        puts "parent_node : #{@parent_node.id}"
        puts "children : #{@children.select{|c|c.id.length>0}.map{|c|c.id}.join(', ')}" if @children.size > 0
      end

      def accept(v)
        v.visit_case_decl(self)
      end
    end
  end
end

