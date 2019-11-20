

JFLAGS = -g
JC = javac
JVM= java 
FILE=

.SUFFIXES: .java .class


.java.class:
	$(JC) $(JFLAGS) $*.java


HUFFMAN = \
	CapaDomini/ModelDomini/Huffman.java \
	CapaDomini/ModelDomini/Pair.java \
	Drivers/DriverHuffman.java

ZIGZAG = \
	CapaDomini/ModelDomini/ZigZag.java \
	Drivers/DriverZigZag.java

ARXIU = \
        CapaDomini/ModelDomini/Arxiu.java \
        CapaDomini/ModelDomini/Estadistiques.java \
	Drivers/DriverArxiu.java

ARXIUBYTE = \
        CapaDomini/ModelDomini/ArxiuBytes.java \
	CapaDomini/ModelDomini/Arxiu.java \
        CapaDomini/ModelDomini/Estadistiques.java \
	Drivers/DriverArxiuBytes.java

ARXIUBTXT = \
        CapaDomini/ModelDomini/ArxiuTXT.java \
	CapaDomini/ModelDomini/Arxiu.java \
        CapaDomini/ModelDomini/Estadistiques.java \
	Drivers/DriverArxiuTXT.java

ESTADISTIQUESALG = \
        CapaDomini/ModelDomini/Arxiu.java \
        CapaPersistencia/IOArxius.java \
        Drivers/DriverEstadistiques.java

IMATGE = \
	CapaDomini/ModelDomini/ArxiuBytes.java \
	CapaDomini/ModelDomini/Arxiu.java \
        CapaDomini/ModelDomini/Estadistiques.java \
	CapaDomini/ModelDomini/Imatge.java \
	Excepcions/ExtensionIncorrecta.java \
	Excepcions/VersionPPMIncorrecta.java \
	Drivers/DriverImatge.java
        

IMATGECOMPRIMIDA = \
	CapaDomini/ModelDomini/ArxiuBytes.java \
	CapaDomini/ModelDomini/Arxiu.java \
        CapaDomini/ModelDomini/Estadistiques.java \
	CapaDomini/ModelDomini/Imatge.java \
	Excepcions/ExtensionIncorrecta.java \
	Excepcions/VersionPPMIncorrecta.java \
	Drivers/DriverImatgeComprimida.java
        

LZSS = \
	CapaDomini/ModelDomini/Arxiu.java \
        CapaDomini/ModelDomini/ArxiuBytes.java \
        CapaDomini/ModelDomini/LZSS.java \
	CapaDomini/ModelDomini/LZ.java \
	CapaDomini/ModelDomini/Compresor.java \
	CapaDomini/ModelDomini/Estadistiques.java \
	CapaDomini/ModelDomini/EstadistiquesAlg.java \
        CapaPersistencia/IOArxius.java \
        CapaDomini/ModelDomini/ArxiuTXT.java \
	Excepcions/CaracterNoASCII.java \
	Excepcions/ExtensionIncorrecta.java \
	Drivers/DriverLZSS.java

PAIR = \
	CapaDomini/ModelDomini/Pair.java \
	Excepcions/DatosIncorrectos.java \
	Drivers/DriverPair.java
        


ESTADISTIQUES = \
        CapaDomini/ModelDomini/Arxiu.java \
        CapaPersistencia/IOArxius.java \
	CapaDomini/ModelDomini/Estadistiques.java \
        Drivers/DriverEstadistiques.java

IOARXIUS = \
        CapaPersistencia/IOArxius.java \
	Excepcions/ExtensionIncorrecta.java \
	Drivers/DriverIOArxius.java

JEPG = \
	Drivers/DriverJPEG.java \
	CapaDomini/ModelDomini/ArxiuBytes.java \
	CapaDomini/ModelDomini/Arxiu.java \
        CapaDomini/ModelDomini/Estadistiques.java \
        CapaDomini/ModelDomini/ImatgeComprimida.java \
        CapaDomini/ModelDomini/Imatge.java \
        CapaDomini/ModelDomini/Estadistiques.java \
	CapaDomini/ModelDomini/Huffman.java \
	CapaDomini/ModelDomini/ZigZag.java \
	CapaDomini/ModelDomini/Pair.java \
        CapaPersistencia/IOArxius.java \
	CapaDomini/ModelDomini/JPEG.java \
	CapaDomini/Controladors/DTOImatge.java \
	Excepcions/DatosIncorrectos.java \
	Excepcions/ExtensionIncorrecta.java \
	Excepcions/VersionPPMIncorrecta.java 

LZ78 = \
        CapaDomini/ModelDomini/ArxiuTXT.java \
        CapaDomini/ModelDomini/Arxiu.java \
        CapaDomini/ModelDomini/Estadistiques.java \
        CapaDomini/ModelDomini/LZ78.java \
        CapaPersistencia/IOArxius.java \
	Excepcions/CaracterNoASCII.java \
	Excepcions/ExtensionIncorrecta.java \
	Drivers/DriverLZ78.java

COMPRIMIR = \
	CapaDomini/ModelDomini/Arxiu.java \
	CapaDomini/ModelDomini/ArxiuBytes.java \
        CapaDomini/Controladors/ControladorComprimir.java \
        CapaPersistencia/IOArxius.java \
	CapaDomini/ModelDomini/Estadistiques.java \
        CapaDomini/ModelDomini/ImatgeComprimida.java \
        CapaDomini/ModelDomini/Imatge.java \
        CapaDomini/ModelDomini/Estadistiques.java \
	CapaDomini/ModelDomini/Huffman.java \
	CapaDomini/ModelDomini/ZigZag.java \
	CapaDomini/ModelDomini/Pair.java \
	CapaDomini/ModelDomini/JPEG.java \
	CapaDomini/Controladors/DTOImatge.java \
	Excepcions/DatosIncorrectos.java \
	Excepcions/ExtensionIncorrecta.java \
	Excepcions/VersionPPMIncorrecta.java \
	Excepcions/CaracterNoASCII.java \
        Drivers/DriverControladorComprimir.java

DESCOMPRIMIR = \
        CapaDomini/ModelDomini/Arxiu.java \
	CapaDomini/ModelDomini/ArxiuBytes.java \
        CapaDomini/Controladors/ControladorComprimir.java \
        CapaPersistencia/IOArxius.java \
	CapaDomini/ModelDomini/Estadistiques.java \
        CapaDomini/ModelDomini/ImatgeComprimida.java \
        CapaDomini/ModelDomini/Imatge.java \
        CapaDomini/ModelDomini/Estadistiques.java \
	CapaDomini/ModelDomini/Huffman.java \
	CapaDomini/ModelDomini/ZigZag.java \
	CapaDomini/ModelDomini/Pair.java \
	CapaDomini/ModelDomini/JPEG.java \
	CapaDomini/Controladors/DTOImatge.java \
	Excepcions/DatosIncorrectos.java \
	Excepcions/ExtensionIncorrecta.java \
	Excepcions/VersionPPMIncorrecta.java \
	Excepcions/CaracterNoASCII.java \
        Drivers/DriverControladorDescomprimir.java

LZW = \
	CapaDomini/ModelDomini/ArxiuTXT.java \
	CapaDomini/ModelDomini/Arxiu.java \
	CapaDomini/ModelDomini/Estadistiques.java \
	CapaDomini/ModelDomini/LZW.java \
	CapaPersistencia/IOArxius.java \
	Excepcions/CaracterNoASCII.java \
	Excepcions/ExtensionIncorrecta.java \
	Drivers/DriverLZW.java


MAIN = Drivers/DriverLZW

LIBS = 'LIB/*:src/main/java'

default: junit_lzw cl_lzw cl_lz78 cl_comprimir cl_descomprimir cl_jepg cl_ioarxiu cl_estadistiques cl_imatgecomprimida cl_lzss cl_estadistiquesalg cl_pair cl_arxiubyte cl_arxiutxt cl_arxiu cl_imatge cl_zigzag cl_huffman

cl_lzw: $(LZW:.java=.class)
	jar cmf ./Manifests/manifest_LZW.txt ../EXE/DriverLZW/driverLZW.jar $(LZW:.java=.class)

cl_lz78: $(LZ78:.java=.class)
	jar cmf ./Manifests/manifest_LZ78.txt ../EXE/DriverLZ78/driverLZ78.jar $(LZ78:.java=.class)

cl_comprimir: $(COMPRIMIR:.java=.class)
	jar cmf ./Manifests/manifest_COMPRIMIR.txt ../EXE/DriverComprimir/driverCOMPRIMIR.jar $(COMPRIMIR:.java=.class)

cl_descomprimir: $(DESCOMPRIMIR:.java=.class)
	jar cmf ./Manifests/manifest_DESCOMPRIMIR.txt ../EXE/DriverDescomprimir/driverDESCOMPRIMIR.jar $(DESCOMPRIMIR:.java=.class)

cl_jepg: $(JEPG:.java=.class)
	jar cmf ./Manifests/manifest_JEPG.txt ../EXE/DriverJEPG/driverJEPG.jar $(JEPG:.java=.class)

cl_ioarxiu: $(IOARXIUS:.java=.class)
	jar cmf ./Manifests/manifest_IOARXIUS.txt ../EXE/DriverIOArxiu/driverIOARXIU.jar $(IOARXIUS:.java=.class)

cl_estadistiques: $(ESTADISTIQUES:.java=.class)
	jar cmf ./Manifests/manifest_ESTADISTIQUES.txt ../EXE/DriverEstadistiques/driverESTADISTIQUES.jar $(ESTADISTIQUES:.java=.class)

cl_lzss: $(LZSS:.java=.class)
	jar cmf ./Manifests/manifest_LZSS.txt ../EXE/DriverLZSS/driverLZSS.jar $(LZSS:.java=.class)

cl_estadistiquesalg: $(ESTADISTIQUESALG:.java=.class)
	jar cmf ./Manifests/manifest_ESTADISTIQUESALG.txt ../EXE/DriverEstadistiquesAlg/driverESTADISTIQUESALG.jar $(ESTADISTIQUESALG:.java=.class)

cl_pair: $(PAIR:.java=.class)
	jar cmf ./Manifests/manifest_PAIR.txt ../EXE/DriverPair/driverPAIR.jar $(PAIR:.java=.class)

cl_arxiubyte: $(ARXIUBYTE:.java=.class)
	jar cmf ./Manifests/manifest_ARXIUBYTES.txt ../EXE/DriverArxiuBytes/driverARXIUBYTES.jar $(ARXIUBYTE:.java=.class)

cl_arxiutxt: $(ARXIUBTXT:.java=.class)
	jar cmf ./Manifests/manifest_ARXIUTXT.txt ../EXE/DriverArxiuTxt/driverArxiuTXT.jar $(ARXIUBTXT:.java=.class)

cl_arxiu: $(ARXIU:.java=.class)
	jar cmf ./Manifests/manifest_ARXIU.txt ../EXE/DriverArxiu/driverARXIU.jar $(ARXIU:.java=.class)

cl_imatge: $(IMATGE:.java=.class)
	jar cmf ./Manifests/manifest_IMATGE.txt ../EXE/DriverImatge/driverIMATGE.jar $(IMATGE:.java=.class)

cl_imatgecomprimida: $(IMATGECOMPRIMIDA:.java=.class)
	jar cmf ./Manifests/manifest_IMATGECOMPRIMIDA.txt ../EXE/DriverImatgeComprimida/driverIMATGECOMPRIMIDA.jar $(IMATGECOMPRIMIDA:.java=.class)

cl_zigzag : $(ZIGZAG:.java=.class)
	jar cmf ./Manifests/manifest_ZIGZAG.txt ../EXE/DriverZigZag/driverZigZag.jar $(ZIGZAG:.java=.class)


cl_huffman : $(HUFFMAN:.java=.class)
	jar cmf ./Manifests/manifest_HUFFMAN.txt ../EXE/DriverHuffman/driverHuffman.jar $(HUFFMAN:.java=.class)

junit_lzw: $(JUNIT:.java=.class)
	javac -g -cp ./Manifests/manifest_JUNIT.txt ../EXE/JUNIT/JUNITtest.jar $(LIBS) Test/LZWTest.java Excepcions/CaracterNoASCII.java Excepcions/ExtensionIncorrecta.java

	
run: $(MAIN).class
	$(JVM) $(MAIN)

clean:
	$(RM) *.class
