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

    def has_beaten(self, player2):
        return (self.points >= 4 or player2.points >= 4) and self.points_difference(player2) >= 2

    def has_the_advantage(self, player2):
        return (self.points >= 4 or player2.points >= 4) and self.points_difference(player2) == 1

    def points_difference(self, player_2):
        return self.points - player_2.points

    def __str__(self):
        return self.name
