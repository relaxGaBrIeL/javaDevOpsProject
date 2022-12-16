# Projet Java DevOps



## Started :

### Git convention

#### Branch :
    - main or master
    - Dev
    - features
#### Types de commit
    - build : changements qui affectent le système de build ou des dépendances externes
    - ci : changements concernant les fichiers et scripts d’intégration ou de configuration 
    - feat : ajout d’une nouvelle fonctionnalité
    - fix : correction d’un bug
    - perf : amélioration des performances
    - refactor : modification qui n’apporte ni nouvelle fonctionalité ni d’amélioration de performances
    - style : changement qui n’apporte aucune alteration fonctionnelle ou sémantique (indentation, mise en forme, ajout
      d’espace, renommante d’une variable…)
    - docs : rédaction ou mise à jour de documentation
    - test : ajout ou modification de tests
    - revert : annuler un commit précédent

#### Commit example :

```
<type>(<portée>): <sujet>
<description>
<footer>

● Portée : emplacement dans l’application (Messagerie, panneau, Groups ...)

● Sujet : cour et explicite (moins de 50 caractères), verbe à l’impératif

● Description : plus de détails sur le commit, verbe à l’impératif

● Footer : Infos supplémentaires,  tâches associées, ...