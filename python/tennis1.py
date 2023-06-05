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
        points_difference = self.p1points - self.p2points
        game_tied = self.points_difference() == 0
        if game_tied and self.p1points >= 3:
            return "Deuce"
        if game_tied:
            return "%s-All" % TennisGame1.points_as_text(self.p1points)
        if (self.p1points >= 4 or self.p2points >= 4) and self.points_difference() == 1:
            return "Advantage %s" % self.player1Name
        if (self.p1points >= 4 or self.p2points >= 4) and self.points_difference() == -1:
            return "Advantage %s" % self.player2Name
        if (self.p1points >= 4 or self.p2points >= 4) and self.points_difference() >= 2:
            return "Win for %s" % self.player1Name
        if self.p1points >= 4 or self.p2points >= 4 and self.points_difference() <= -2:
            return "Win for %s" % self.player2Name

        return "%s-%s" % (TennisGame1.points_as_text(self.p1points), TennisGame1.points_as_text(self.p2points))

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
