val textFile = sc.textFile("fr.openfoodfacts.org.products.csv")
val burglar = textFile.map(_.split("\t").filter(_.size > 8).map(a => (a(3),a(6))))
for ((a,e) <- burglar){
	println(a)
}
val myfiltered = burglar.filter(line => line.contains("BURGLARY"))
println(myfiltered.count())
for(a <- myfiltered){
	println(a)
}
