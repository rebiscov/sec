# Rapport

## Step 4

Dans la step 4 nous définissons un automate fini pour chaque composant et nous en faisons le produit. Le problème vient que la lisibilité du code généré en est impactée. De plus il est difficile de prendre en compte certains type de transition comme "si le 7seg passe à 5, alors allumer la led". Nous avons créer une sorte de lib pour prendre en compte les différents composants. Créer de nouveau composant n'est donc pas très difficile. On regrette toutefois qu'il n'y ait pas une classe de laquelle pourraient plus facilement hériter nos composants pour éviter les conversions de type comme on le fait actuellement.

## Step 5

Cette fois on utilise un modèle réactif comme base à notre meta model. Ce modèle est beaucoup plus lisible que le précédent et beaucoup plus expressif. On utilise des régistres que l'on change à notre guise. On peut donc facilement résoudre le problème précédent de cette manière en jouant avec les flags ("si le 7seg passe à 5, alors allumer la led"). Certains problèmes sont toutefois toujours d'actulaité comme la création de nouveaux composants. On aurait également pu pour faciliter l'utilisation du seven-seg en créant un autre type de registre qui peut prendre plusieurs valeurs numériques, plutôt que simplement des booléens.

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

## Step 4

Q: What are the pros/cons associated to the meta-modeling approach? What is the cost of defining a meta-model? What is difficult in this activity?
A: Définir un méta-modèle permet ensuite de définir simplement un nouveau langage à utiliser avec notre arduino. Il faut en revanche bien penser le méta modèle dès le départ pour qu'il soit facilement utilisable et pratique.

Q: From the user point of view, what does it change? Is the approach usable for large apps?
A: Il devient beaucoup plus facile de créer un programme en utilisant ce méta modèle. Il est beaucoup plus simple de créer de larges applications de manière beaucoup plus lisible. De plus en écrivant une lib comme nous l'avons fait il devient beaucoup plus facile d'utiliser de nouveaux composants.

Q: Consider the LED app and the counter one as two separate models. Is it possible to automate the creation of the final app based on these two models?
A: La solution à ce problème est de faire un produit de deux automates en considérant que les deux modèles de base sont des automates finis.

Q: What about the readability of the generated code compared to the previous one “by hand”? Its debugging capabilities ? Its extensiveness?
A: Le code généré n'est en revanche pas très lisible. Il utilise beaucoup de fonctions. Il faut suivre l'exécution à la main pour le débuggage ce qui peut être fastidieux quand le nombre de fonctions croit beaucoup. De plus ce code va conduire à stack overflow.

Q: Explain the interest of modeling in terms of genericity, functional property verification.
A: L'intérêt vient du fait que chaque fonction peut être vérifiée de la même manière.

## Step 5

Q: Compare how this modeling solution and the previous one match the domain, especially regarding expressiveness and scalability.
A: Cette solution match beaucoup mieux le domaine. Elle est plus expressive. En effet, on peut définir des actions selon l'état dans lequel est le 7seg par exemple, ce qui était impossible précedemment.

Q: What is the cost (e.g., modeling, code generation) of a new feature for the developer?
A: Il est assez facile pour le développeur de rajouter une nouvelle feature. Il n'y a rien a changé dans la génération de code, simplement une classe ou deux à rajouter dans la lib qui pourra ensuite être utilisée pour écrire un programme.

Q: What about scalability of the modeling paradigm itself?
A: 


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

Guillaume CLUZEl: Diego Vaquero-Melchor, Javier Palomares, Esther Guerra, Juan de Lara: Active Domain-Specific Languages: Making Every Mobile User a Modeller. 75-82.
