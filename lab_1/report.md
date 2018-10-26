# Rapport

## Step 6

Le travail sur la step6 était relativement court. Le code dans `glue_arduino.c` avait déjà été réaliser dans les steps précédentes. Il a fallu ajouter dans `main.c` une fonction pour gérer le seven segments (cette fonction prend un entier dans [0,...,9] et change l'état du seven segments). Enfin, nous avons programmé un programme Lustre très simple qui se charge simplement de de changer l'état de la LED et de mettre le compteur à zéro si il y a pression, et sinon de simplement incrémenter le compteur.

## Step 7

Pour la step7, nous avons peu changé le langage proposé. Nous avons ajouté la possibilité de lier un state à un sensor (dans notre cas un bouton). Cela permet (comme dans la step4) de donner un état suivant alternatif au cas où le bouton serait activé. Nous avons ajouté le support pour le seven segments. Le but était aussi de définir plusieurs états à la fois (pour le seven segments) afin de ne pas avoir à définir les 10 états à la main, ce que nous n'avons pu réaliser par manque de temps.

# Réponses aux questions

## Step 1

Q: What can we say about readability of this code? What are the skills required to write such a program?
A: Le code est vraiment peu lisible. Par exemple pour le 7-seg, il est difficile de voir qu'on assigne un 0 sur le port D quand il est représenté par `PORTD = 0b10000000;`
Il faut donc bien connaître le fonctionnement bas niveau de l'arduino et connaître le C.

Q: Regarding the application domain, could you characterize the expressivity? The configurability of the code to change pins or behavior? Its debugging capabilities?
A: Le code est très expressif puisqu'on est à très bas niveau. En revanche il est difficilement lisible et c'est assez fastidieux d'écrire ce genre de code.

Q: Regarding the performance of the output code, what kind of parallelism is expressed by the use of the DDRx registers?
A: On peut modifier la valeur de plusieurs pins en même temps, en parallèle.

Q: What if we add additional tasks in the micro-controller code, with the same frequency? With a different frequency?
A: On peut changer la fréquence du microcontroller facilement. Il faudrait prendre le pgcd des différentes fréquences.

## Step 2

Q: Is the readability problem solved?
A: La lisibilité du code est meilleure que précedemment, mais la librairie n'offre que peu d'abstractions comparé au fonctionnement global de l'arduino. Il faut toujours avoir de bonnes connaissances bas niveau.

Q: What kind of parallelism can still be expressed?
A: On ne peut plus définir la valeur de plusieurs pins en même temps. En revanche l'utilisateur aura toujours une impression de parallélisme puisqu'on change les valeurs des pins très rapidement.

Q: Who is the public targeted by this “language”?
A: Le public est visé est un public habitué à programmer en C et qui connaît le fonctionnement de l'arduino, mais qui ne cherche pas à faire quelque chose de compliqué.

Q: Is this language extensible enough to support new features?
A: Le langage est très expressif.

Q: What is the price for the developer?
A: Le développeur doit développer de lui-même les nouvelles features ce qui peut être long et difficile puisqu'il part de rien.

## Step 3

Q: Does introducing a convention solve the readability issue?
A: Le code est plus lisible car il est plus modulaire. Une partie des fonctions définissent des FLAG, d'autres les utilisent. La répartition des tâches est plus claire.

Q: How to extend an app with a new feature? Does the approach prevent one to perform invasive changes in the existing behavior to introduce a new one?
A: C'est assez facile de rajouter une nouvelle feature sans toucher au reste. Il suffit d'ajouter un flag qui sera modifié par un bouton et lu par notre nouveau composant.

Q: How to extend the code so that to support new features, e.g., memory-less tasks, state-full tasks, different frequencies?
A: Il n'y a rien à faire pour les memory-less tasks, pour créer des state-full tasks, il faut ajouter des registres, et pour gérer plusieurs fréquences il faut faire un calcul de pgcd

## Step 6

Q: Who is the intended user for such a language?
A: Les utilisateurs désirant une forte abstraction vis à vis du hardware

Q: What is the cost of reusing this existing DSL for the developer in terms of code?
A: Le développeur doit implanter chaque nouveau composants pour conserver une abstraction forte

Q: What is the cost of adding a new task of our domain?
A: Si le composant existe déjà, le coût est minime, néanmoins si l'utilisateur veut utiliser un composant nouveau (un panneau LED par exemple), le développeur doit d'abord programmer l'interface avec le code Lustre

Q: What is the cost of adding a new hardware target?
A: Il est facile d'ajouter un nouveau composant (c'est le cas du seven segments du moins...) mais il y a un travail à faire pour chaque composant

Q: The Lustre language comes with its own ecosystem (test, formal verification), what are the generic properties we can imagine to prove from our domain?
A: On peut prouver que la LED va changer d'état après pression du bouton, on peut prouver que si le seven segments est à l'état i, à l'étape suivante, il sera à l'étape (i+1) mod 10 si le bouton n'a pas été activé etc...

## Step 7

Q: Who is the intended user ? What about the tooling associated to the language?
A: Un utilisateur désirant une forte abstraction (comme dans la step 4) mais ayant toujours des connaissances en automates et des connaissances de base en hardware (ici pour l'arduino)

Q: More generally, what is the cost of such an approach?
A: Les coûts sont les mêmes que pour la step4, sauf que si le modèle de la step4 est changé, il en revient au développeur de modifier le langage de la step7

Q: To what extent is the language fragile to the introduction of new features?
A: Il faut modifier l'implantation du modèle de la step4 et le langage

Q: What is the relationship between the meta-model and the grammar?
A: La grammaire contient une partie du meta-modèle (un état contient une action par exemple)

Q: How to validate that the defined syntax is the right one?
A: Il faut prouver que le langage instancie correctement la modèle, puis que le modèle est correct

## Choix des papiers
Vincent RÉBISCOUL: Julien Lange, Nicholas Ng, Bernardo Toninho, Nobuko Yoshida: A static verification framework for message passing in Go using behavioural types. 1137-1148.
