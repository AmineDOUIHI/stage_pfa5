import spacy

class NerExtractor:
    def extract_entities(self, text):
        nlp = spacy.load("fr_core_news_sm")
        doc = nlp(text)
        entities = []
        for ent in doc.ents:
            entities.append({"text": ent.text, "label": ent.label_})
        return entities