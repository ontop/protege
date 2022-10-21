package org.protege.owlapi.inference.cls;

import org.semanticweb.owlapi.model.*;

import java.util.Set;


public class ParentClassExtractor implements OWLAxiomVisitor {

    private NamedClassExtractor extractor = new NamedClassExtractor();

    private OWLClass current;


    public void setCurrentClass(OWLClass current) {
        this.current = current;
    }


    public void reset() {
        extractor.reset();
    }


    public Set<OWLClass> getResult() {
        return extractor.getResult();
    }


    public void visit(OWLSubClassOfAxiom axiom) {
        axiom.getSuperClass().accept(extractor);
    }


    public void visit(OWLEquivalentClassesAxiom axiom) {
        for (OWLClassExpression desc : axiom.getClassExpressions()) {
            if (desc.equals(current)) {
                continue;
            }
            desc.accept(extractor);
        }
    }
}

