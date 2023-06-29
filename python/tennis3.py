# -*- coding: utf-8 -*-

class TennisGame3:
    def __init__(self, player1Name, player2Name):
        self.player1_name = player1Name
        self.player2_name = player2Name
        self.player1_points = 0
        self.player2_points = 0

    def won_point(self, n):
        if n == self.player1_name:
            self.player1_points += 1
        else:
            self.player2_points += 1

    def score(self):
        if (self.player1_points < 4 and self.player2_points < 4) and (self.player1_points + self.player2_points < 6):
            points_to_text = {0: 'Love', 1: 'Fifteen', 2: 'Thirty', 3: 'Forty'}
            s = points_to_text[self.player1_points]
            if self.player1_points == self.player2_points:
                return s + "-All"
            else:
                return s + "-" + points_to_text[self.player2_points]
        else:
            if self.player1_points == self.player2_points:
                return "Deuce"
            s = self.player1_name if self.player1_points > self.player2_points else self.player2_name
            return "Advantage " + s if ((self.player1_points - self.player2_points) * (self.player1_points - self.player2_points) == 1) else "Win for " + s
