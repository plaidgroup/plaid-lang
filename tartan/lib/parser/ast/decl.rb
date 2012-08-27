module Parser
  module AST
    class Decl
      attr_accessor :linum, :id, :indent_level, :parent_node, :children, :and_states
      attr_reader :primary_state
      def initialize(linum, indent_level, id, and_states, primary_state)
        @linum = linum
        @id = id
        @indent_level = indent_level
        @and_states = and_states
        @primary_state = primary_state

        @parent_node = nil
        @children = []
      end

      def add_child(child)
        @children.push(child)
        child.parent_node = self
      end

      def state_id
        @primary_state.id
      end

      def to_s
        puts "-- decl --"
        puts "linum: #{@linum}"
        puts "id : #{@id}"
        puts "indent_level : #{@indent_level}"
        puts "and_states : #{@and_states.map{|s|s.id}.join(', ')}"
        puts "primary_state : #{@primary_state.id}"
        puts "parent_node : #{@parent_node.id}" if @parent_node
        puts "children : #{@children.select{|c|c.id.length>0}.map{|c|c.id}.join(', ')}" if @children.size > 0
      end

      def accept(v)
        v.visit_variable_decl(self)
      end
    end
  end
end
