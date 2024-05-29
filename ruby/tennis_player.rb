# frozen_string_literal: true

class TennisPlayer
  attr_reader :name, :points

  def initialize(name)
    @name = name
    @points = 0
  end

  def won_point
    @points += 1
  end

  def points_difference_over(opponent:)
    points - opponent.points
  end
end
