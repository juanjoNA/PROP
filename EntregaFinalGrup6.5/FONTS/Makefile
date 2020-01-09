

JFLAGS = -g
JC = javac
JVM= java 
CLASSPATH = 
FILE=

.SUFFIXES: .java .class

MAIN = CapaPresentacio/MainFrame

default: generar_class generar_jar

generar_class:
	$(JC) $(JFLAGS) -d classes/ -cp ./lib/json-20131018.jar:./lib/json-simple-1.1.jar:./lib/json-simple-1.1.1.jar:. CapaDomini/Controladors/ArxCarpetaComp.java \
	CapaDomini/Controladors/DTOComparar.java \
	CapaDomini/Controladors/DTOImatge.java \
	CapaPersistencia/IOArxius.java \
	CapaPersistencia/EstadisticasDisc.java \
	CapaPersistencia/MyObjectOutputStream.java \
	CapaDomini/ModelDomini/Estadistiques.java \
	CapaDomini/ModelDomini/Arxiu.java \
	CapaDomini/ModelDomini/ArxiuBytes.java \
	CapaDomini/ModelDomini/ArxiuTXT.java \
	CapaDomini/ModelDomini/Compresor.java \
	CapaDomini/ModelDomini/Huffman.java \
	CapaDomini/ModelDomini/Imatge.java \
	CapaDomini/ModelDomini/ImatgeComprimida.java \
	CapaDomini/ModelDomini/JPEG.java \
	CapaDomini/ModelDomini/LZ78.java \
	CapaDomini/ModelDomini/LZSS.java \
	CapaDomini/ModelDomini/LZW.java \
	CapaDomini/ModelDomini/Pair.java \
	CapaDomini/ModelDomini/ZigZag.java \
	CapaDomini/Controladors/ControladorAlgoritmes.java \
	CapaDomini/Controladors/ControladorComparar.java \
	CapaDomini/Controladors/ControladorComprimir.java \
	CapaDomini/Controladors/ControladorComprimirCarpeta.java \
	CapaDomini/Controladors/ControladorDescomprimir.java \
	CapaDomini/Controladors/ControladorDescomprimirCarpeta.java \
	CapaDomini/Controladors/ControladorEstadisticas.java \
	Excepcions/CaracterNoASCII.java \
	Excepcions/DatosIncorrectos.java \
	Excepcions/ExtensionIncorrecta.java \
	Excepcions/VersionPPMIncorrecta.java \
	CapaPresentacio/ComparacioFitxers.java \
	CapaPresentacio/Comparar.java \
	CapaPresentacio/Comprimir.java \
	CapaPresentacio/Descomprimir.java \
	CapaPresentacio/Estadistiques.java \
	CapaPresentacio/Inici.java \
	CapaPresentacio/MainFrame.java 

generar_jar:
	jar cmf ./manifest.txt ../EXE/compresorProp.jar -C classes ./CapaDomini -C classes ./CapaPersistencia -C classes ./Excepcions ./icons -C classes ./CapaPresentacio

	
run: $(MAIN).class
	$(JVM) $(MAIN)

clean:
	$(RM) *.class
