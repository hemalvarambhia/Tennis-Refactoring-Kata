# frozen_string_literal: true

class TennisPlayer
  attr_reader :name, :points

  def initialize(name)
    @name = name
    @points = 0
  end
end
