require_relative 'tennis_player'
class TennisGame1

  def initialize(player1Name, player2Name)
    @player1_name = player1Name
    @p1points = 0
    @player_1 = TennisPlayer.new(player1Name)

    @player2_name = player2Name
    @p2points = 0
    @player_2 = TennisPlayer.new(player2Name)
  end
        
  def won_point(playerName)
    if playerName == "player1"
      @p1points += 1
      @player_1.won_point
    elsif playerName == 'player2'
      @p2points += 1
      @player_2.won_point
    end
  end

  POINTS_TO_SCORE = {
    0 => "Love",
    1 => "Fifteen",
    2 => "Thirty",
    3 => "Forty",
  }
  private_constant :POINTS_TO_SCORE

  def score
    return "Win for #{@player1_name}" if player_1_won?
    return "Win for #{@player2_name}" if player_2_won?

    return "Advantage #{@player1_name}" if advantage_player_1?
    return "Advantage #{@player2_name}" if advantage_player_2?

    return "Deuce" if deuce?

    if points_difference.zero?
      return {
          0 => "Love-All",
          1 => "Fifteen-All",
          2 => "Thirty-All",
      }[@p1points]
    end

    "#{POINTS_TO_SCORE[@p1points]}-#{POINTS_TO_SCORE[@p2points]}"
  end

  private

  def deuce?
    @p1points >= 3 && points_difference.zero?
  end

  def player_1_won?
    (@p1points >= 4 or @p2points >= 4) && points_difference >= 2
  end

  def player_2_won?
    (@p1points >= 4 or @p2points >= 4) && points_difference <= -2
  end

  def advantage_player_1?
    (@p1points >= 4 or @p2points >= 4) && points_difference == 1
  end

  def advantage_player_2?
    (@p1points >= 4 or @p2points >= 4) && points_difference == -1
  end

  def points_difference
    @p1points - @p2points
  end
end

class TennisGame2
  def initialize(player1Name, player2Name)
    @player1Name = player1Name
    @player2Name = player2Name
    @p1points = 0
    @p2points = 0
  end
      
  def won_point(playerName)
    if playerName == @player1Name
      p1Score()
    else
      p2Score()
    end
  end

  def score
    result = ""
    if (@p1points == @p2points and @p1points < 3)
      if (@p1points==0)
        result = "Love"
      end
      if (@p1points==1)
        result = "Fifteen"
      end
      if (@p1points==2)
        result = "Thirty"
      end
      result += "-All"
    end
    if (@p1points==@p2points and @p1points>2)
        result = "Deuce"
    end
    
    p1res = ""
    p2res = ""
    if (@p1points > 0 and @p2points==0)
      if (@p1points==1)
        p1res = "Fifteen"
      end
      if (@p1points==2)
        p1res = "Thirty"
      end
      if (@p1points==3)
        p1res = "Forty"
      end
      p2res = "Love"
      result = p1res + "-" + p2res
    end
    if (@p2points > 0 and @p1points==0)
      if (@p2points==1)
        p2res = "Fifteen"
      end
      if (@p2points==2)
        p2res = "Thirty"
      end
      if (@p2points==3)
        p2res = "Forty"
      end
      
      p1res = "Love"
      result = p1res + "-" + p2res
    end
    
    if (@p1points>@p2points and @p1points < 4)
      if (@p1points==2)
        p1res="Thirty"
      end
      if (@p1points==3)
        p1res="Forty"
      end
      if (@p2points==1)
        p2res="Fifteen"
      end
      if (@p2points==2)
        p2res="Thirty"
      end
      result = p1res + "-" + p2res
    end
    if (@p2points>@p1points and @p2points < 4)
      if (@p2points==2)
        p2res="Thirty"
      end
      if (@p2points==3)
        p2res="Forty"
      end
      if (@p1points==1)
        p1res="Fifteen"
      end
      if (@p1points==2)
        p1res="Thirty"
      end
      result = p1res + "-" + p2res
    end
    if (@p1points > @p2points and @p2points >= 3)
      result = "Advantage " + @player1Name
    end
    if (@p2points > @p1points and @p1points >= 3)
      result = "Advantage " + @player2Name
    end
    if (@p1points>=4 and @p2points>=0 and (@p1points-@p2points)>=2)
      result = "Win for " + @player1Name
    end
    if (@p2points>=4 and @p1points>=0 and (@p2points-@p1points)>=2)
      result = "Win for " + @player2Name
    end
    result
  end

  def setp1Score(number)
    (0..number).each do |i|
        p1Score()
    end
  end

  def setp2Score(number)
    (0..number).each do |i|
      p2Score()
    end
  end

  def p1Score
    @p1points +=1
  end
  
  def p2Score
    @p2points +=1
  end
end

class TennisGame3
  def initialize(player1Name, player2Name)
    @p1N = player1Name
    @p2N = player2Name
    @p1 = 0
    @p2 = 0
  end
      
  def won_point(n)
    if n == @p1N
        @p1 += 1
    else
        @p2 += 1
    end
  end
  
  def score
    if (@p1 < 4 and @p2 < 4) and (@p1 + @p2 < 6)
      p = ["Love", "Fifteen", "Thirty", "Forty"]
      s = p[@p1]
      @p1 == @p2 ? s + "-All" : s + "-" + p[@p2]
    else
      if (@p1 == @p2)
        "Deuce"
      else
        s = @p1 > @p2 ? @p1N : @p2N
        (@p1-@p2)*(@p1-@p2) == 1 ? "Advantage " + s : "Win for " + s
      end
    end
  end
end
