CREATE SCHEMA IF NOT EXISTS INVOICE;

CREATE TABLE invoice.person (
                                id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                                taxId VARCHAR(11) NOT NULL UNIQUE,
                                name VARCHAR(100) NOT NULL
);

CREATE TABLE invoice.invoicing (
                                   id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                                   taxId VARCHAR(11) NOT NULL UNIQUE,
                                   amount BIGINT NOT NULL
);

INSERT INTO invoice.person (taxId, name) VALUES ('12345678901', 'Ana Silva');
INSERT INTO invoice.person (taxId, name) VALUES ('23456789012', 'Bruno Costa');
INSERT INTO invoice.person (taxId, name) VALUES ('34567890123', 'Carla Pereira');
INSERT INTO invoice.person (taxId, name) VALUES ('45678901234', 'Daniel Oliveira');
INSERT INTO invoice.person (taxId, name) VALUES ('56789012345', 'Eduarda Santos');
INSERT INTO invoice.person (taxId, name) VALUES ('67890123456', 'Felipe Almeida');
INSERT INTO invoice.person (taxId, name) VALUES ('78901234567', 'Gabriela Souza');
INSERT INTO invoice.person (taxId, name) VALUES ('89012345678', 'Henrique Lima');
INSERT INTO invoice.person (taxId, name) VALUES ('90123456789', 'Isabella Rocha');
INSERT INTO invoice.person (taxId, name) VALUES ('01234567890', 'João Mendes');
INSERT INTO invoice.person (taxId, name) VALUES ('13579246810', 'Karina Nascimento');
INSERT INTO invoice.person (taxId, name) VALUES ('24680135791', 'Lucas Martins');
INSERT INTO invoice.person (taxId, name) VALUES ('35791357912', 'Mariana Torres');
INSERT INTO invoice.person (taxId, name) VALUES ('46802468013', 'Nicolas Andrade');
INSERT INTO invoice.person (taxId, name) VALUES ('57913579124', 'Olga Ferreira');
INSERT INTO invoice.person (taxId, name) VALUES ('68024680235', 'Paulo Ribeiro');
INSERT INTO invoice.person (taxId, name) VALUES ('79135791346', 'Quezia Almeida');
INSERT INTO invoice.person (taxId, name) VALUES ('80246802457', 'Ricardo Teixeira');
INSERT INTO invoice.person (taxId, name) VALUES ('91357913568', 'Sofia Pacheco');
INSERT INTO invoice.person (taxId, name) VALUES ('02468024679', 'Tiago Campos');