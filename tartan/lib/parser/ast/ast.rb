module Parser
  module AST
    class AST
      attr_reader :root, :app
      def initialize(root, app)
        @root = root
        @app = app
      end
    end
  end
end
