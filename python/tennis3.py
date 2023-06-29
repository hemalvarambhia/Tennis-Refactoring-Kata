# -*- coding: utf-8 -*-
from player import Player


class TennisGame3:
    def __init__(self, player1Name, player2Name):
        self.player1_name = player1Name
        self.player2_name = player2Name
        self.player1_points = 0
        self.player2_points = 0
        self.player1 = Player(player1Name)
        self.player2 = Player(player2Name)

    def won_point(self, n):
        if n == self.player1_name:
            self.player1_points += 1
            self.player1.won_point()
        else:
            self.player2_points += 1
            self.player2.won_point()

    def score(self):
        if (self.player1.points < 4 and self.player2_points < 4) and (self.player1.points + self.player2_points < 6):
            points_to_text = {0: 'Love', 1: 'Fifteen', 2: 'Thirty', 3: 'Forty'}
            if self.player1.points == self.player2_points:
                return points_to_text[self.player1.points] + "-All"
            else:
                return points_to_text[self.player1.points] + "-" + points_to_text[self.player2_points]
        else:
            if self.player1.points == self.player2_points:
                return "Deuce"
            s = self.player1.name if self.player1.points > self.player2_points else self.player2_name
            return "Advantage " + s if ((self.player1.points - self.player2_points) * (self.player1.points - self.player2_points) == 1) else "Win for " + s
