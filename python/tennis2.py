# -*- coding: utf-8 -*-
from player import Player


class TennisGame2:
    def __init__(self, player1Name, player2Name):
        self.player1 = Player(player1Name)
        self.player2 = Player(player2Name)

    def won_point(self, playerName):
        if playerName == self.player1.name:
            self.player1.won_point()
        else:
            self.player2.won_point()

    def score(self):
        if self.player1.points_difference(self.player2) == 0 and self.player1.points < 3:
            return self.__points_to_text(self.player1.points) + "-All"

        if self.__deuce():
            return "Deuce"

        player_with_advantage = self.__player_with_advantage()
        if player_with_advantage is not None:
            return 'Advantage %s' % player_with_advantage

        winner = self.__winning_player()
        if winner is not None:
            return 'Win for %s' % winner

        return self.__points_to_text(self.player1.points) + "-" + self.__points_to_text(self.player2.points)

    def __winning_player(self):
        if self.player1.has_beaten(self.player2):
            return self.player1
        elif self.player2.has_beaten(self.player1):
            return self.player2

    def __deuce(self):
        return self.player1.points_difference(self.player2) == 0 and self.player1.points >= 3

    def __player_with_advantage(self):
        if self.player1.has_the_advantage(self.player2):
            return self.player1

        if self.player2.has_the_advantage(self.player1):
            return self.player2

    def __points_to_text(self, points):
        return {
            0: 'Love',
            1: 'Fifteen',
            2: 'Thirty',
            3: 'Forty'
        }[points]
