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

  def has_won_against?(opponent:)
    (points >= 4 or opponent.points >= 4) &&
      points_difference_over(opponent: opponent) >= 2
  end

  def has_advantage_over?(opponent:)
    (points >= 4 or opponent.points >= 4) &&
      points_difference_over(opponent: opponent) == 1
  end

  def deuce?(opponent:)
    points >= 3 && points_difference_over(opponent: opponent).zero?
  end

  def points_difference_over(opponent:)
    points - opponent.points
  end

  POINTS_TO_RUNNING_SCORE = {
    0 => "Love",
    1 => "Fifteen",
    2 => "Thirty",
    3 => "Forty",
  }
  private_constant :POINTS_TO_RUNNING_SCORE

  def running_score
    POINTS_TO_RUNNING_SCORE[points]
  end
end
