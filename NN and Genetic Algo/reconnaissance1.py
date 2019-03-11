# -*- coding: utf-8 -*-
"""
Created on Mon Mar  4 16:31:54 2019
Algo de réseau de neurone
@author: Tom Calamaï
"""

import numpy as np
import matplotlib.pyplot as plt
from skimage import io

#Définition de la sigmoid :
l = 0.5
def s(x):
    #return 0.5*(abs(x)+x)/abs(x)
    return 1/(1+np.exp(-x/l))

#Affichage de la fonction de transition (sigmoid)
t = np.linspace(-5,5)
ft = s(t)
plt.plot(t, ft)
plt.title("sigmoid")
plt.show()

#fonction d'erreur (à minimiser)
def E(Msortie):
    Merreur = Msortie - np.eye(6)
    Merreur = Merreur*Merreur
    return sum(sum(Merreur))

#NN comme individu pour évoluer les poids avec un algo génétique
class Individu:
    
    def __init__(self, a, b, M):
        self.P = np.random.uniform(a, b, (6,36))
        self.M = M
    
    #affichage du score de l'individu
    def __str__(self):
        return 'Score :' + str(self.score())
   
    #petite mutation de tous les poids
    def mutation(self, eps):
        self.P = self.P + np.random.uniform(-eps/2, eps/2, (6,36))
     
    #Grande mutation cad réinitialisation :
    def Mutation(self, a, b):
        self.P = np.random.uniform(a, b, (6,36))
      
    #récupération de la matrice des poids
    def get_P(self):
        return self.P
    
    #Croisement entre deux matrices P pour créer le nouvel individu
    def croisement(self, pere, mere):
        self.P = ( pere.get_P() + mere.get_P() ) / 2
     
    #Donne la matrice de sortie en fonction de self.M
    def Msort(self):
        return s(self.P.dot(self.M))
     
    #Retourne le score (qui doit être minimisé)
    def score(self):
        return E(self.Msort())

#Fonction qui permet de classer la population d'individu
def Classement(pop):
    pop.sort(key = lambda x: x.score())
    

#Chargement des images
im1=io.imread("Test1.bmp")
im2=io.imread("TestA.bmp")
im3=io.imread("TestC.bmp")
im4=io.imread("TestO.bmp")
im5=io.imread("TestP.bmp")
im6=io.imread("TestU.bmp")

#Mise en forme des images
im1=im1.reshape((36,1))
im2=im2.reshape((36,1))
im3=im3.reshape((36,1))
im4=im4.reshape((36,1))
im5=im5.reshape((36,1))
im6=im6.reshape((36,1))

#TOutres les images sont mises dans une grande matrice
M = np.concatenate((im1,im2,im3,im4,im5,im6), 1)
M = M/255 #normalisation

#init algorithme génétique :
op_max = 5000
op = 0
meilleurs = []

#init Individus :
a,b = -1,1
n = 30
pop = [Individu(a,b,M) for i in range(n)]

#Proportion des transformation génétique (mutation, ...) :
prop_nulle = 0.05
prop_mutation = 0.60
prop_croisement = 0.30
eps = 0.5

#conditions d'arret :
seuil = 0.2
#La boucle continue tant que le seuil n'est pas atteint ou qu'onà pas réalisé le nombre max de tour
while (op < op_max and (meilleurs == [] or meilleurs[-1] > seuil) ):
    #Classement de la population :
    pmax = len(pop)
    Classement(pop)
    
    #Pour afficher l'évolution de l'erreur  
    meilleurs.append(sum(ind.score() for ind in pop)/len(pop))
    
    #mutation et croisement pour toute la pop
    p = 0
    for individu in pop:
        if(p/pmax > prop_nulle and p/pmax <= (prop_mutation + prop_nulle) ):
            individu.mutation(eps)
        elif(p/pmax <= (prop_croisement + prop_nulle + prop_mutation) and p/pmax > (prop_mutation + prop_nulle)):
            individu.croisement(pop[np.random.randint(0, p)], pop[np.random.randint(0, p)])
        elif(p/pmax > (prop_croisement + prop_nulle + prop_mutation)):
            individu.Mutation(a,b)
        p = p + 1
    op = op + 1           

Classement(pop)
plt.plot(meilleurs)
plt.title("Erreur moyenne")
plt.show()

#affichage du résultat pour le meilleur de la pop finale :
io.imshow(1-pop[0].Msort())

#test :
imTest=io.imread("Testprime.bmp")
imTest=imTest.reshape((36,1))

Result = s(pop[0].get_P().dot(imTest))
print(Result)



