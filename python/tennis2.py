# -*- coding: utf-8 -*-

class TennisGame2:
    def __init__(self, player1Name, player2Name):
        self.player1Name = player1Name
        self.player2Name = player2Name
        self.p1points = 0
        self.p2points = 0

    def won_point(self, playerName):
        if playerName == self.player1Name:
            self.P1Score()
        else:
            self.P2Score()

    def score(self):
        result = ""
        points_to_text = {
            0: 'Love',
            1: 'Fifteen',
            2: 'Thirty',
            3: 'Forty'
        }
        if (self.p1points == self.p2points and self.p1points < 3):
            result = points_to_text[self.p1points]
            return result + "-All"
        if (self.p1points==self.p2points and self.p1points>2):
            return "Deuce"

        P1res = ""
        P2res = ""
        if (self.p1points > 0 and self.p2points==0):
            P1res = points_to_text.get(self.p1points, '')
            P2res = points_to_text[self.p2points]
            result = P1res + "-" + P2res
        if (self.p2points > 0 and self.p1points==0):
            P2res = points_to_text.get(self.p2points, '')
            P1res = points_to_text[self.p1points]
            result = P1res + "-" + P2res
        if (self.p1points>self.p2points and self.p1points < 4):
            P1res = points_to_text[self.p1points]
            P2res = points_to_text[self.p2points]
            result = P1res + "-" + P2res
        if (self.p2points>self.p1points and self.p2points < 4):
            if (self.p1points==1):
                P1res="Fifteen"
            if (self.p1points==2):
                P1res="Thirty"
            P2res = points_to_text[self.p2points]
            result = P1res + "-" + P2res

        if (self.p1points > self.p2points and self.p2points >= 3):
            result = "Advantage player1"

        if (self.p2points > self.p1points and self.p1points >= 3):
            result = "Advantage player2"

        if (self.p1points>=4 and self.p2points>=0 and (self.p1points-self.p2points)>=2):
            result = "Win for player1"
        if (self.p2points>=4 and self.p1points>=0 and (self.p2points-self.p1points)>=2):
            result = "Win for player2"
        return result

    def SetP1Score(self, number):
        for i in range(number):
            self.P1Score()

    def SetP2Score(self, number):
        for i in range(number):
            self.P2Score()

    def P1Score(self):
        self.p1points +=1


    def P2Score(self):
        self.p2points +=1
