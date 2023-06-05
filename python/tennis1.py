# -*- coding: utf-8 -*-

class TennisGame1:

    def __init__(self, player1Name, player2Name):
        self.player1Name = player1Name
        self.player2Name = player2Name
        self.p1points = 0
        self.p2points = 0

    def won_point(self, playerName):
        if playerName == self.player1Name:
            self.p1points += 1
        else:
            self.p2points += 1

    def score(self):
        result = ""
        game_tied = self.p1points == self.p2points
        if game_tied and self.p1points >= 3:
            return "Deuce"
        if game_tied:
            result = {
                0: "Love-All",
                1: "Fifteen-All",
                2: "Thirty-All",
            }[self.p1points]
            return result
        elif (self.p1points >= 4 or self.p2points >= 4):
            points_difference = self.p1points - self.p2points
            if (points_difference == 1):
                result = "Advantage %s" % self.player1Name
            elif (points_difference == -1):
                result = "Advantage %s" % self.player2Name
            elif (points_difference >= 2):
                result = "Win for player1"
            else:
                result = "Win for player2"
        else:
            result = "%s-%s" % (self.points_as_text(self.p1points), self.points_as_text(self.p2points))
        return result

    def points_as_text(self, points_scored):
        return {
            0: "Love",
            1: "Fifteen",
            2: "Thirty",
            3: "Forty",
        }[points_scored]
