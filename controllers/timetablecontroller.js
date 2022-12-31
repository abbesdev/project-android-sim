//import { validationResult } from 'express-validator'; // Importer express-validator

import TimeTable from '../models/timetable.js';
import Classes from '../models/classes.js';


export function getAll(req, res) {
    console.log("dds2");
   
    TimeTable
    .find({})
    // .where('onSale').equals(true) // Si 'OnSale' a la valeur true
    // .where('year').gt(2000).lt(2022) // Si 2000 < 'year' < 2022 
    // .where('name').in(['DMC5', 'RE8', 'NFS']) // Si 'name' a l'une des valeurs du tableau
    // .limit(10) // Récupérer les 10 premiers seulement
    // .sort('-year') // Tri descendant (enlever le '-' pour un tri ascendant)
    // .select('name') // Ne retourner que les attributs mentionnés (séparés par des espace si plusieurs)
    // .exec() // Executer la requête
    .then(
        
        Classes.findOne({ "nameClasse": req.params.classes[0] },(err, doc2) => {
        if(err) {
            console.log("dds");
        }
        else {
           req.params.className = doc2.nameClasse
           console.log("dd");

        }}),
    
        
       
        docs => {
        
        res.status(200).json(docs);
    })
    .catch(err => {
        res.status(500).json({ error: err });
    });
}


export function addOnce(req, res) {
    // Trouver les erreurs de validation dans cette requête et les envelopper dans un objet
    if(!validationResult(req).isEmpty()) {
        res.status(400).json({ errors: validationResult(req).array() });
    }
    else {
        // Invoquer la méthode create directement sur le modèle
        TimeTable
        .create({
        
         title: req.body.title,
            classes: req.body.classes,
            startdate: req.body.startdate,
            enddate: req.body.enddate,
            
           
        })
        .then(newSubject => {
            res.status(200).json(newSubject);
        })
        .catch(err => {
            res.status(500).json({ error: err });
        });
    }
}

/*
export function getOnce(req, res) {
    TimeTable
    .findOne({ "nameSubject": req.params.nameSubject })
    .then(doc => {
        res.status(200).json(doc);
    })
    .catch(err => {
        res.status(500).json({ error: err });
    });
}
*/