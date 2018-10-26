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
A: C'est assez facile de rajouter une nouvelle feature sans toucher au reste. Il suffit d'ajouter un flag qui sera modifié par un boutton et lu par notre nouveau composant.

Q: How to extend the code so that to support new features, e.g., memory-less tasks, state-full tasks, different frequencies?
A: 