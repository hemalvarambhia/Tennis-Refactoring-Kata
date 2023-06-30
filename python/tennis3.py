# -*- coding: utf-8 -*-
from player import Player
import math


class TennisGame3:
    def __init__(self, player1Name, player2Name):
        self.player1 = Player(player1Name)
        self.player2 = Player(player2Name)

    def won_point(self, n):
        if n == self.player1.name:
            self.player1.won_point()
        else:
            self.player2.won_point()

    def score(self):
        if (self.player1.points < 4 and self.player2.points < 4) and (self.player1.points + self.player2.points < 6):
            points_to_text = {0: 'Love', 1: 'Fifteen', 2: 'Thirty', 3: 'Forty'}
            if self.player1.points_difference(self.player2) == 0:
                return points_to_text[self.player1.points] + "-All"
            else:
                return points_to_text[self.player1.points] + "-" + points_to_text[self.player2.points]
        else:
            if self.player1.points_difference(self.player2) == 0:
                return "Deuce"
            if self.player1.points > self.player2.points:
                leading_player = self.player1.name
            else:
                leading_player = self.player2.name
            advantage = math.pow(self.player1.points_difference(self.player2), 2) == 1
            if advantage:
                return "Advantage " + leading_player
            else:
                return "Win for " + leading_player
