# -*- coding: utf-8 -*-
from player import Player
import math


class TennisGame3:
    def __init__(self, server, receiver):
        self.server = Player(server)
        self.receiver = Player(receiver)

    def won_point(self, n):
        if n == self.server.name:
            self.server.won_point()
        else:
            self.receiver.won_point()

    def score(self):
        if self.__deuce():
            return "Deuce"

        player_with_advantage = self.__player_with_advantage()
        if player_with_advantage:
            return "Advantage %s" % player_with_advantage

        winner = self.__winning_player()
        if winner:
            return "Win for %s" % winner

        points_to_text = {0: 'Love', 1: 'Fifteen', 2: 'Thirty', 3: 'Forty'}
        if self.server.points_difference(self.receiver) == 0:
            return points_to_text[self.server.points] + "-All"

        return points_to_text[self.server.points] + "-" + points_to_text[self.receiver.points]

    def __deuce(self):
        return (self.server.points >= 3 and self.receiver.points >= 3) and \
            self.server.points_difference(self.receiver) == 0

    def __player_with_advantage(self):
        if self.server.has_the_advantage(self.receiver):
            return self.server
        elif self.receiver.has_the_advantage(self.server):
            return self.receiver

    def __winning_player(self):
        if self.server.has_beaten(self.receiver):
            return self.server
        elif self.receiver.has_beaten(self.server):
            return self.receiver
