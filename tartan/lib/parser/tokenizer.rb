require 'parser.rb'

module Parser
  class Tokenizer
    include Parser::Base

    def initialize(f)
      @f = f
      @q = []
      @linum = 0
      self.tokenize
    end

    def next_token
      @q.shift
    end

    def tokenize
      while !@f.eof
        new_line
        @q.push Token.new(:INDENT, count_indent(@line), @linum)

        until @line.empty? do
          case @line
          when /\A\s+/, /\A\#.*/
            ;
          when /\A#{ASSIGNMENT}/
            @q.push Token.new(:ASSIGNMENT, ASSIGNMENT, @linum)
          when /\A#{STATE_SEPARATOR}/
            @q.push Token.new(:STATE_SEPARATOR, STATE_SEPARATOR, @linum)
          when /\A#{CASE_DECL}/
            @q.push Token.new(:CASE_DECL, CASE_DECL, @linum)
          when /\A#{PRIMARY_STATE_DECL}/
            @q.push Token.new(:PRIMARY_STATE_DECL, PRIMARY_STATE_DECL, @linum)
          when /\A#{L_PLAID_LITERAL}/
            @line = $'
            current_line = @linum
            @q.push Token.new(:PLAID_LITERAL, tokenize_plaid_literal, current_line)
            next
          when /\A[a-z_]\w*/
            @q.push Token.new(:CASE_DECL, CASE_DECL, @linum) if $& == CASE_DECL
            @q.push Token.new(:PRIMARY_STATE_DECL, PRIMARY_STATE_DECL, @linum) if $& == PRIMARY_STATE_DECL
            @q.push Token.new(:VARIABLE, $&, @linum)
          when /\A[A-Z]\w*/
            @q.push Token.new(:ID, $&, @linum)
          else
            raise RuntimeError, "unknown token at #{@line}"
          end
          @line = $'
        end
        @q.push Token.new(:EOL, nil, @linum)
      end
    end

    def tokenize_plaid_literal
      literal = String.new(L_PLAID_LITERAL)
      nest = 1

      begin
        new_line if @line.empty?
        until @line.empty? do
          case @line
          when /\A"(?:[^"\\]+|\\.)"/, /\A'(?:[^'\\]+|\\.)'/
            literal << $&
          when /\A#{L_PLAID_LITERAL}/
            literal << $&
            nest += 1
          when /\A#{R_PLAID_LITERAL}/
            literal << $&
            nest -= 1
            if nest == 0
              @line = $'
              return literal
            end
          when /\A\s+/, /\A\S/
            literal << $&
          else
            raise RuntimeError, "unknown token in Plaid Literal at #{@line}"
          end
          @line = $'
        end
      end while !@f.eof

      raise RuntimeError, "Plaid literal is not closed properly."
    end

    def new_line
      @line = @f.gets
      @linum += 1
    end
  end

  class Token
    attr_reader :type, :value, :linum

    def initialize(type, value, linum)
      @type = type
      @value = value
      @linum = linum
    end

    def to_s
      "l.#{@linum}, type:#{@type}, value:#{@value}"
    end
  end
end

