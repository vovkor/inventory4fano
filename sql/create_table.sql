CREATE TABLE z#accessions
    ( accessions_id    NUMBER(6)
    , accenumb  VARCHAR2(1000) CONSTRAINT emp_accenumb_z#accessions NOT NULL
    , collmunb     VARCHAR2(1000)
    , collcode     VARCHAR2(1000)
    , expedition     VARCHAR2(1000)
    , genus     VARCHAR2(1000)
    , species     VARCHAR2(1000)
    , spauthor  VARCHAR2(1000)
    , subtaxa VARCHAR2(1000)
    , subtauthor VARCHAR2(1000)
    , accename_rus VARCHAR2(1000)
    , accename VARCHAR2(1000)
    , acqdate VARCHAR2(1000)
    , origcty VARCHAR2(1000)
    , collsite VARCHAR2(1000)
    , collsite_rus VARCHAR2(1000)
    , latitude VARCHAR2(1000)
    , longitude VARCHAR2(1000)
    , elevation VARCHAR2(1000)
    , colldate VARCHAR2(1000)
    , bredcode VARCHAR2(1000)
    , sampstat VARCHAR2(1000)
    , ancest VARCHAR2(1000)
    , ancest_rus VARCHAR2(1000)
    , collsrc VARCHAR2(1000)
    , doncty VARCHAR2(1000)
    , donorcode VARCHAR2(1000)
    , donornumb VARCHAR2(1000)
    , othernumb VARCHAR2(1000)
    , duplsite VARCHAR2(1000)
    , storage VARCHAR2(1000)
    , lifform VARCHAR2(1000)
    , intrnumb VARCHAR2(1000)
    , remarks VARCHAR2(1000)
    ,CONSTRAINT accessions_id_pk PRIMARY KEY (accessions_id)
    );