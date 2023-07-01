# -*- coding: utf-8 -*-
from player import Player
import math


class TennisGame3:
    def __init__(self, server, receiver):
        self.player1 = Player(server)
        self.player2 = Player(receiver)

    def won_point(self, n):
        if n == self.player1.name:
            self.player1.won_point()
        else:
            self.player2.won_point()

    def score(self):
        if (self.player1.points >= 3 and self.player2.points >= 3):
            if self.player1.points_difference(self.player2) == 0:
                return "Deuce"

        if (self.player1.points <= 3 and self.player2.points <= 3) and (self.player1.points + self.player2.points <= 5):
            points_to_text = {0: 'Love', 1: 'Fifteen', 2: 'Thirty', 3: 'Forty'}
            if self.player1.points_difference(self.player2) == 0:
                return points_to_text[self.player1.points] + "-All"
        else:
            if self.player1.points > self.player2.points:
                leading_player = self.player1.name
            else:
                leading_player = self.player2.name
            advantage = abs(self.player1.points_difference(self.player2)) == 1
            if advantage:
                return "Advantage " + leading_player
            else:
                return "Win for " + leading_player

        return points_to_text[self.player1.points] + "-" + points_to_text[self.player2.points]
