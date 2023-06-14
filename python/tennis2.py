# -*- coding: utf-8 -*-
from player import Player
class TennisGame2:
    def __init__(self, player1Name, player2Name):
        self.player1Name = player1Name
        self.player2Name = player2Name
        self.p1points = 0
        self.p2points = 0
        self.player1 = Player(player1Name)
        self.player2 = Player(player2Name)

    def won_point(self, playerName):
        if playerName == self.player1Name:
            self.p1points += 1
        else:
            self.p2points += 1

    def score(self):
        if self.p1points == self.p2points and self.p1points < 3:
            return self.__points_to_text(self.p1points) + "-All"

        if self.p1points - self.p2points == 0 and self.p1points >= 3:
            return "Deuce"

        if self.p1points - self.p2points == 1 and self.p2points >= 3:
            return "Advantage player1"

        if self.p2points - self.p1points == 1 and self.p1points >= 3:
            return "Advantage player2"

        if self.__player_1_beat_player_2():
            return  "Win for player1"
        if self.__player_2_beat_player_1():
            return "Win for player2"

        return self.__points_to_text(self.p1points) + "-" + self.__points_to_text(self.p2points)

    def __player_2_beat_player_1(self):
        return self.p2points >= 4 and self.p1points >= 0 and (self.p2points - self.p1points) >= 2

    def __player_1_beat_player_2(self):
        return self.p1points >= 4 and self.p2points >= 0 and (self.p1points - self.p2points) >= 2

    def __points_to_text(self, points):
        return {
            0: 'Love',
            1: 'Fifteen',
            2: 'Thirty',
            3: 'Forty'
        }[points]

