(ns user
  (:require
    [datomic.api]
    [clojure.java.io]))


(comment

  (def schema (-> (clojure.java.io/resource "seattle-schema.edn")
                  slurp read-string))
  (datomic.api/create-database "datomic:mem://foo")
  (def conn (datomic.api/connect "datomic:mem://foo"))
  (datomic.api/transact conn schema)

  (def data0 (-> (clojure.java.io/resource "seattle-data0.edn")
                 slurp read-string))

  (datomic.api/transact conn data0)

  (def $ (datomic.api/db conn))

  (datomic.api/q '[:find (pull ?e [:community/name])
                   :where
                   [?e :community/name]]
                 $)

  )