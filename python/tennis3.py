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
        if self.__deuce():
            return "Deuce"

        if self.player1.has_the_advantage(self.player2):
            return "Advantage %s" % self.player1
        elif self.player2.has_the_advantage(self.player1):
            return "Advantage %s" % self.player2

        if self.player1.has_beaten(self.player2):
            winning_player = self.player1
            return "Win for %s" % winning_player
        elif self.player2.has_beaten(self.player1):
            winning_player = self.player2
            return "Win for %s" % winning_player

        points_to_text = {0: 'Love', 1: 'Fifteen', 2: 'Thirty', 3: 'Forty'}
        if self.player1.points_difference(self.player2) == 0:
            return points_to_text[self.player1.points] + "-All"

        return points_to_text[self.player1.points] + "-" + points_to_text[self.player2.points]

    def __deuce(self):
        return (self.player1.points >= 3 and self.player2.points >= 3) and \
            self.player1.points_difference(self.player2) == 0
