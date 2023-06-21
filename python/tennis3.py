# -*- coding: utf-8 -*-

class TennisGame3:
    def __init__(self, player1Name, player2Name):
        self.player1_name = player1Name
        self.p2N = player2Name
        self.player1_points = 0
        self.p2 = 0

    def won_point(self, n):
        if n == self.player1_name:
            self.player1_points += 1
        else:
            self.p2 += 1

    def score(self):
        if (self.player1_points < 4 and self.p2 < 4) and (self.player1_points + self.p2 < 6):
            p = ["Love", "Fifteen", "Thirty", "Forty"]
            s = p[self.player1_points]
            return s + "-All" if (self.player1_points == self.p2) else s + "-" + p[self.p2]
        else:
            if (self.player1_points == self.p2):
                return "Deuce"
            s = self.player1_name if self.player1_points > self.p2 else self.p2N
            return "Advantage " + s if ((self.player1_points - self.p2) * (self.player1_points - self.p2) == 1) else "Win for " + s
