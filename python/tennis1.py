# -*- coding: utf-8 -*-
class Player:
    def __init__(self, name):
        self.name = name
        self.points = 0

    def won_point(self):
        self.points += 1

    def name(self):
        return self.name

    def points(self):
        return self.points

    def __str__(self):
        return self.name


class TennisGame1:

    def __init__(self, player1_name, player2_name):
        self.player1 = Player(player1_name)
        self.player2 = Player(player2_name)

    def won_point(self, player_name):
        if player_name == self.player1.name:
            self.player1.won_point()
        else:
            self.player2.won_point()

    def score(self):
        deuce = self.game_tied() and self.player1.points >= 3
        if deuce:
            return "Deuce"
        if self.game_tied():
            return "%s-All" % TennisGame1.points_as_text(self.player1.points)
        player_with_advantage = self.player_with_advantage()
        if player_with_advantage is not None:
            return "Advantage %s" % player_with_advantage
        winning_player = self.winner()
        if winning_player is not None:
            return "Win for %s" % str(winning_player)

        return "%s-%s" % (TennisGame1.points_as_text(self.player1.points), TennisGame1.points_as_text(self.player2.points))

    def game_tied(self):
        return self.points_difference() == 0

    def winner(self):
        if (self.player1.points >= 4 or self.player2.points >= 4) and self.points_difference() >= 2:
            return self.player1.name
        if (self.player1.points >= 4 or self.player2.points >= 4) and self.points_difference() <= -2:
            return self.player2.name

    def player_with_advantage(self):
        if (self.player1.points >= 4 or self.player2.points >= 4) and self.points_difference() == 1:
            return self.player1.name
        if (self.player1.points >= 4 or self.player2.points >= 4) and self.points_difference() == -1:
            return self.player2.name

    def points_difference(self):
        return self.player1.points - self.player2.points

    @staticmethod
    def points_as_text(points_scored):
        return {
            0: "Love",
            1: "Fifteen",
            2: "Thirty",
            3: "Forty",
        }[points_scored]
