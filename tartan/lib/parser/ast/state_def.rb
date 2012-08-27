module Parser
  module AST
    class StateDef
      attr_accessor :linum, :id, :literal, :and_states, :children, :parent

      def initialize(linum, id, literal=nil, and_states=[], parent = nil)
        @linum = linum
        @id = id
        @literal = literal
        @and_states = and_states
        @parent = parent
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
        puts "-- state def --"
        puts "id : #{@id}"
        puts "literal : #{@literal}" if literal
        puts "and_state : #{@and_states.map{|s|s.id}.join(', ')}" if @and_states
        puts "parent : #{@parent}" if @parent
      end

      def to_state_literal
        "#{@id}#{@literal}"
      end

      def accept(v)
        v.visit_state_def(self)
      end
    end
  end
end
