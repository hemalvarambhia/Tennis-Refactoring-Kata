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

  def has_advantage_over?(opponent:)
    (points >= 4 or opponent.points >= 4) &&
      points_difference_over(opponent: opponent) == 1
  end

  def points_difference_over(opponent:)
    points - opponent.points
  end
end
