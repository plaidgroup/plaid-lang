require 'codegenerator/plaid/structure'
require 'codegenerator/plaid/style'

module Codegenerator
  module Plaid
    def and_states?(node)
      node.and_states.size > 0
    end

    def and_states_literal(node, prefix = "", suffix = "")
      return "" unless and_states?(node)
      prefix + node.and_states.map{|s|s.to_state_literal}.join(" with ") + suffix
    end
  end
end
