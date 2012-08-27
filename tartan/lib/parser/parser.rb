require 'parser/ast'

module Parser
  class Parser

    include Base

    attr_reader :app
    def initialize(tokenizer, filename = "")
      @tokenizer = tokenizer
      @filename = filename
      @root = nil
      @current = nil
      @app = false
    end

    def parse
      @indent = @tokenizer.next_token
      @lookahead = @tokenizer.next_token

      @root = @current = root_from_filename unless @lookahead.type == :ID
      begin
        case @lookahead.type
        when :CASE_DECL
          case_decl
        when :ID
          @current = state_def
          @root ||= @current
        when :VARIABLE
          named_variable_decl
        when :ASSIGNMENT
          annonymouse_variable_decl
        when :EOL
          ;
        else
          raise RuntimeError, "unrecognizable token : #{@lookahead}"
        end

        @indent = @tokenizer.next_token
        @lookahead = @tokenizer.next_token
      end while @indent && @lookahead

      return AST::AST.new(@root, @app)
    end

    def root_from_filename
      @app = true
      return AST::StateDef.new(0, File.basename(@filename, ".*"))
    end

    def state_def
      match(:ID)

      linum, id, literal = state_literal

      case @lookahead.type
      when :ASSIGNMENT, :STATE_SEPARATOR
        ;
      when :EOL
        return AST::StateDef.new(linum, id, literal)
      else
        raise RuntimeError, "unrecognizable token : #{@lookahead}"
      end

      @lookahead = @tokenizer.next_token
      case @lookahead.type
      when :ID
        return AST::StateDef.new(linum, id, literal, and_states)
      else
        raise RuntimeError, "unrecognizable token : #{@lookahead}"
      end
    end

    def named_variable_decl
      match(:VARIABLE)

      name = @lookahead.value
      @lookahead = @tokenizer.next_token

      return variable_decl(name)
    end

    def annonymouse_variable_decl
      return variable_decl("")
    end

    def variable_decl(variable_name)
      match(:ASSIGNMENT)

      linum = @lookahead.linum
      and_states = []
      primary_state = nil

      begin
        @lookahead = @tokenizer.next_token
        case @lookahead.type
        when :PRIMARY_STATE_DECL
          @lookahead = @tokenizer.next_token
          case @lookahead.type
          when :ID
            line, id, literal = state_literal
            primary_state = AST::StateDef.new(line, id, literal)
            and_states << primary_state
          else
            raise RuntimeError, "unrecognizable token : #{@lookahead}"
          end
        when :ID
          line, id, literal = state_literal
          and_states << AST::StateDef.new(line, id, literal)
        when :EOL
          skip_token(:INDENT)
          redo
        end
      end while @lookahead.type == :STATE_SEPARATOR

      match(:EOL)

      decl = AST::Decl.new(linum, @indent.value, variable_name, and_states, primary_state || and_states.last)
      insert_decl(decl)
    end

    def case_decl
      match(:CASE_DECL)

      @lookahead = @tokenizer.next_token
      match(:ID)

      linum, id, literal = state_literal
      decl = AST::CaseDecl.new(linum, @indent.value, id, literal)

      case @lookahead.type
      when :STATE_SEPARATOR, :ASSIGNMENT
        @lookahead = @tokenizer.next_token
        decl.and_states = and_states
      else
        ;
      end

      insert_decl decl
    end

    def state_literal
      match(:ID)

      linum = @lookahead.linum
      id = @lookahead.value
      literal = nil

      @lookahead = @tokenizer.next_token

      if @lookahead.type == :PLAID_LITERAL
        literal = @lookahead.value
        @lookahead = @tokenizer.next_token
      end

      return [linum, id, literal]
    end

    def and_states
      match(:ID)

      linum, id, literal = state_literal
      and_states = [AST::StateDef.new(linum, id, literal)]

      while @lookahead.type == :STATE_SEPARATOR
        @lookahead = @tokenizer.next_token
        case @lookahead.type
        when :ID
          linum, id, literal = state_literal
          and_states << AST::StateDef.new(linum, id, literal)
        when :EOL
          skip_token(:INDENT)
          redo
        end
      end

      match(:EOL)

      return and_states
    end

    def match(type)
      raise RuntimeError, "unmatch token : #{@lookahead}" unless @lookahead.type == type
    end

    def insert_decl(decl)
      if(@current)
        @current = @current.parent_node while @current != @root  && decl.indent_level <= @current.indent_level
        @current.add_child decl
        @current = decl
      end
    end

    def skip_token(type)
      @lookahead = @tokenizer.next_token
      raise RuntimeError, "try to skip unknown type of token : #{@lookahead}" unless @lookahead.type == type
    end
  end
end

