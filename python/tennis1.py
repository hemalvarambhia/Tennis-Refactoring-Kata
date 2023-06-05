# -*- coding: utf-8 -*-

class TennisGame1:

    def __init__(self, player1Name, player2Name):
        self.player1Name = player1Name
        self.player2Name = player2Name
        self.p1points = 0
        self.p2points = 0

    def won_point(self, player_name):
        if player_name == self.player1Name:
            self.p1points += 1
        else:
            self.p2points += 1

    def score(self):
        game_tied = self.points_difference() == 0
        if game_tied and self.p1points >= 3:
            return "Deuce"
        if game_tied:
            return "%s-All" % TennisGame1.points_as_text(self.p1points)
        player_with_advantage = self.player_with_advantage()
        if player_with_advantage:
            return "Advantage %s" % player_with_advantage
        winning_player = None
        if (self.p1points >= 4 or self.p2points >= 4) and self.points_difference() >= 2:
            winning_player = self.player1Name
        if (self.p1points >= 4 or self.p2points >= 4) and self.points_difference() <= -2:
            winning_player = self.player2Name

        if winning_player is not None:
            return "Win for %s" % winning_player

        return "%s-%s" % (TennisGame1.points_as_text(self.p1points), TennisGame1.points_as_text(self.p2points))

    def player_with_advantage(self):
        if (self.p1points >= 4 or self.p2points >= 4) and self.points_difference() == 1:
            return self.player1Name
        if (self.p1points >= 4 or self.p2points >= 4) and self.points_difference() == -1:
            return self.player2Name

    def points_difference(self):
        return self.p1points - self.p2points

    @staticmethod
    def points_as_text(points_scored):
        return {
            0: "Love",
            1: "Fifteen",
            2: "Thirty",
            3: "Forty",
        }[points_scored]
