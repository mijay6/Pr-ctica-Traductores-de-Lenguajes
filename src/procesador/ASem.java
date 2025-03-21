package procesador;

import tslib.TS_Gestor.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

//Clase que almacena todos los atributos de los simbolos gramaticales
//Cada atrbuto con su setter y getter correspondiente

class Atributos {

	private Integer pos;
	private String tipo;
	private String cod;
	private Integer exit;
	private String ret;
	private Integer ancho;
	private Integer longs;
	private String referencia;
	private String etiqueta;
    private Integer val;
    private String lex;
	private Integer program_count;

	public Atributos() {
		this.pos = null;
		this.tipo = null;
		this.cod = null;
		this.exit = null;
		this.ret = null;
		this.ancho = null;
		this.longs = null;
		this.referencia = null;
		this.etiqueta = null;
        this.val = null;
        this.lex = null;
		this.program_count = null;

	}
	
	public void setCodigo(String cod) { 
		this.cod = cod; 
	}
	
	public String getCodigo() { 
		return cod; 
	}
	
    public void setVal(Integer val) {
        this.val = val;
    }

    public Integer getVal() {
        return val;
    }

    public void setLex(String lex) {
        this.lex = lex;
    }

    public String getLex() {
        return lex;
    }
	
	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public Integer getPos() {
		return pos;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setExit(Integer exit) {
		this.exit = exit;
	}

	public Integer getExit() {
		return exit;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public String getRet() {
		return ret;
	}

	public void setAncho(Integer ancho) {
		this.ancho = ancho;
	}

	public Integer getAncho() {
		return ancho;
	}

	public void setLong(Integer longs) {
		this.longs = longs;
	}

	public Integer getLongs() {
		return longs;
	}

	public void setReferencia(String ref) {
		this.referencia = ref;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setEtiqueta(String et) {
		this.etiqueta = et;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setProgramCount(Integer i) {
		this.program_count = i;
	}

	public int getProgramCount() {
		return this.program_count;
	}
}

public class ASem {

	public static boolean tsGlobal;
	private static boolean zonaDeclaracion;
	private static Integer despGlobal, despLocal;
	private static Integer numEtiq;
	private static final Map<Integer, Supplier<Atributos>> ruleMap = new HashMap<>();

	// Destruir tabla y imprimirlo en el fichero asignado
	private static void destroy(Tabla tabla) {
		Procesador.gestorTS.write(tabla);
		Procesador.gestorTS.destroy(tabla);
	}

	// Debug method
	@SuppressWarnings("unused")
	private static void imprimirPila(Atributos[] atb) {
		for (int i = atb.length - 1; i > 0; i--) {
			System.out.println("PILA: " + atb[i].getPos() + " i:" + i);
			System.out.println("PILA: " + atb[i].getTipo() + " i:" + i);
		}
	}

	// Debug method
	@SuppressWarnings("unused")
	private static void pila_info(Atributos[] atb) {
		for (int i = 0; i < atb.length; i++) {
			System.out.println("PILA " + i + " Pos: " + atb[i].getPos() + " Tipo: " + atb[i].getTipo() + " Ret: "
					+ atb[i].getRet() + " Exit: " + atb[i].getExit() + " Ancho " + atb[i].getAncho());
		}
	}

	static {
		// TS: Activar debug y crear atributos
		Procesador.gestorTS.activarDebug();
		Procesador.gestorTS.createAtributo("desplazamiento", DescripcionAtributo.DIR, TipoDatoAtributo.ENTERO);
		Procesador.gestorTS.createAtributo("etiqueta", DescripcionAtributo.ETIQUETA, TipoDatoAtributo.CADENA);
		Procesador.gestorTS.createAtributo("PasoParametros", DescripcionAtributo.MODO_PARAM, TipoDatoAtributo.LISTA);
		Procesador.gestorTS.createAtributo("tipoParametros", DescripcionAtributo.TIPO_PARAM, TipoDatoAtributo.LISTA);
		Procesador.gestorTS.createAtributo("tipoRetorno", DescripcionAtributo.TIPO_RET, TipoDatoAtributo.CADENA);
		Procesador.gestorTS.createAtributo("numParametro", DescripcionAtributo.NUM_PARAM, TipoDatoAtributo.ENTERO);
		Procesador.gestorTS.createAtributo("modoParametro", DescripcionAtributo.PARAM, TipoDatoAtributo.ENTERO);

		// Desde 2 porque tabla principal tiene el numero #1

		zonaDeclaracion = false;
		tsGlobal = true;
		numEtiq = 2;

		ruleMap.put(1, ASem::acc1);
		ruleMap.put(2, ASem::acc2);
		ruleMap.put(3, ASem::acc3);
		ruleMap.put(4, ASem::acc4);
		ruleMap.put(5, ASem::acc5);
		ruleMap.put(6, ASem::acc6);
		ruleMap.put(7, ASem::acc7);
		ruleMap.put(8, ASem::acc8);
		ruleMap.put(9, ASem::acc9);
		ruleMap.put(10, ASem::acc10);
		ruleMap.put(11, ASem::acc11);
		ruleMap.put(12, ASem::acc12);
		ruleMap.put(13, ASem::acc13);
		ruleMap.put(14, ASem::acc14);
		ruleMap.put(15, ASem::acc15);
		ruleMap.put(16, ASem::acc16);
		ruleMap.put(17, ASem::acc17);
		ruleMap.put(18, ASem::acc18);
		ruleMap.put(19, ASem::acc19);
		ruleMap.put(20, ASem::acc20);
		ruleMap.put(21, ASem::acc21);
		ruleMap.put(22, ASem::acc22);
		ruleMap.put(23, ASem::acc23);
		ruleMap.put(24, ASem::acc24);
		ruleMap.put(25, ASem::acc25);
		ruleMap.put(26, ASem::acc26);
		ruleMap.put(27, ASem::acc27);
		ruleMap.put(28, ASem::acc28);
		ruleMap.put(29, ASem::acc29);
		ruleMap.put(30, ASem::acc30);
		ruleMap.put(31, ASem::acc31);
		ruleMap.put(32, ASem::acc32);
		ruleMap.put(33, ASem::acc33);
		ruleMap.put(34, ASem::acc34);
		ruleMap.put(35, ASem::acc35);
		ruleMap.put(36, ASem::acc36);
		ruleMap.put(37, ASem::acc37);
		ruleMap.put(38, ASem::acc38);
		ruleMap.put(39, ASem::acc39);
		ruleMap.put(40, ASem::acc40);
		ruleMap.put(41, ASem::acc41);
		ruleMap.put(42, ASem::acc42);
		ruleMap.put(43, ASem::acc43);
		ruleMap.put(44, ASem::acc44);
		ruleMap.put(45, ASem::acc45);
		ruleMap.put(46, ASem::acc46);
		ruleMap.put(47, ASem::acc47);
		ruleMap.put(48, ASem::acc48);
		ruleMap.put(49, ASem::acc49);
		ruleMap.put(50, ASem::acc50);
		ruleMap.put(51, ASem::acc51);
		ruleMap.put(52, ASem::acc52);
		ruleMap.put(53, ASem::acc53);
		ruleMap.put(54, ASem::acc54);
		ruleMap.put(55, ASem::acc55);
		ruleMap.put(56, ASem::acc56);
		ruleMap.put(57, ASem::acc57);
		ruleMap.put(58, ASem::acc58);
		ruleMap.put(59, ASem::acc59);
		ruleMap.put(60, ASem::acc60);
		ruleMap.put(61, ASem::acc61);
		ruleMap.put(62, ASem::acc62);
		ruleMap.put(63, ASem::acc63);
		ruleMap.put(64, ASem::acc64);
		ruleMap.put(65, ASem::acc65);
		ruleMap.put(66, ASem::acc66);
		ruleMap.put(67, ASem::acc67);
		ruleMap.put(68, ASem::acc68);
		ruleMap.put(69, ASem::acc69);
		ruleMap.put(70, ASem::acc70);
		ruleMap.put(71, ASem::acc71);
		ruleMap.put(72, ASem::acc72);
		ruleMap.put(73, ASem::acc73);
		ruleMap.put(74, ASem::acc74);
		ruleMap.put(75, ASem::acc75);
		ruleMap.put(76, ASem::acc76);
		ruleMap.put(77, ASem::acc77);
		ruleMap.put(78, ASem::acc78);
		ruleMap.put(79, ASem::acc79);
		ruleMap.put(80, ASem::acc80);
		ruleMap.put(81, ASem::acc81);
		ruleMap.put(82, ASem::acc82);
		ruleMap.put(83, ASem::acc83);
		ruleMap.put(84, ASem::acc84);
		ruleMap.put(85, ASem::acc85);
		ruleMap.put(86, ASem::acc86);
		ruleMap.put(87, ASem::acc87);
		ruleMap.put(88, ASem::acc88);
		ruleMap.put(89, ASem::acc89);
		ruleMap.put(90, ASem::acc90);
		ruleMap.put(91, ASem::acc91);
		ruleMap.put(92, ASem::acc92);
		ruleMap.put(93, ASem::acc93);
		ruleMap.put(94, ASem::acc94);
		ruleMap.put(95, ASem::acc95);
		ruleMap.put(96, ASem::acc96);
		ruleMap.put(97, ASem::acc97);
		ruleMap.put(98, ASem::acc98);
		ruleMap.put(99, ASem::acc99);
		ruleMap.put(100, ASem::acc100);
	}

	public static boolean zonaDec() {
		return zonaDeclaracion;
	}

	// Seleccionar funcion de analisis semantico correspondiente a la regla recibida
	// del Analizador Sintactico
	public static Atributos selectFunction(Integer numRegla) {
		return ruleMap.getOrDefault(numRegla, () -> null).get();
	}

	
	//  
	// 
	
	/*
	private String getTuplaTS(int pos) {
	    // Determinar el ámbito
	    if (tsGlobal) {
	        return "{VAR_GLOBAL," + Procesador.gestorTS.getValorAtributoEnt(pos, "desplazamiento") + "}";
	    } else {
	        // Verificar si es parámetro
	        if (Procesador.gestorTS.getValorAtributoEnt(pos, "numParametro") > 0) {
	            if (Procesador.gestorTS.getValorAtributoEnt(pos, "modoParametro") == 1) {
	                return "{PAR_REF," + Procesador.gestorTS.getValorAtributoEnt(pos, "desplazamiento") + "}";
	            } else {
	                return "{PAR," + Procesador.gestorTS.getValorAtributoEnt(pos, "desplazamiento") + "}";
	            }
	        }
	        return "{VAR_LOCAL," + Procesador.gestorTS.getValorAtributoEnt(pos, "desplazamiento") + "}";
	    }
	}
	*/
	
	
	
	// TODO: Modificar las acciones semanticas para añadir acciones correspondientes al GCI
	
	// Implementacion de todos los analisis semanticos: acc1 - acc100
	// ***************************************************************************************************************
	private static Atributos acc1() {
		Regla reg = MT_ASINT.getRegla(1);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos rAtb = atb[1];
		if (rAtb.getProgramCount() < 1) {
			GestorError.writeError("Debe existir una declaración de program");
		} else if (rAtb.getProgramCount() > 1) {
			GestorError.writeError("Solo puede existir una declaración de program");
		}
		destroy(Tabla.GLOBAL);
		return new Atributos();
	}

	private static Atributos acc2() {
		Procesador.gestorTS.createTSGlobal();
		tsGlobal = true;
		despGlobal = 0;
		zonaDeclaracion = true;
		return new Atributos();
	}

	private static Atributos acc3() {
		Regla reg = MT_ASINT.getRegla(3);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos rAtb = atb[1];
		Atributos res = new Atributos();
		res.setProgramCount(1 + rAtb.getProgramCount());
		return res;
	}

	private static Atributos acc4() {
		Regla reg = MT_ASINT.getRegla(4);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos rAtb = atb[1];
		Atributos res = new Atributos();
		res.setProgramCount(rAtb.getProgramCount());
		return res;
	}

	private static Atributos acc5() {
		Regla reg = MT_ASINT.getRegla(5);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos rAtb = atb[1];
		Atributos res = new Atributos();
		res.setProgramCount(rAtb.getProgramCount());
		return res;
	}

	private static Atributos acc6() {
		Atributos res = new Atributos();
		res.setProgramCount(0);
		return res;
	}
	
		
	// TODO: Modificacion de la regla para el programa principal/estructura general
	// Aquí se podria insertar el cuarteto que marca la etiqueta "main"
	
	private static Atributos acc7() {
		Regla reg = MT_ASINT.getRegla(7);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos bloqueAtb = atb[3];
		if (bloqueAtb.getTipo().equals("tipo_error")) {
			GestorError.writeError("Error detectado en el desarrollo del cuerpo del Programa principal");
		}
		if (!bloqueAtb.getRet().equals("tipo_ok") && !bloqueAtb.getRet().equals("")) {
			GestorError.writeError("Programa Principal con instruccion de retorno no vacio");
		}
		if (bloqueAtb.getExit() > 0) {
			GestorError.writeError("Exit fuera de bucle detectado en Programa Principal");
		}
		destroy(Tabla.LOCAL);
		tsGlobal = true;
		zonaDeclaracion = true;
		return new Atributos();
	}

	// TODO: Modificacion para procedimientos
	
	private static Atributos acc8() {
		Regla reg = MT_ASINT.getRegla(8);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos pidAtb = atb[1];
		Procesador.gestorTS.setTipo(pidAtb.getPos(), "procedimiento"); // Checkear error de gestor TS.
		Procesador.gestorTS.setValorAtributoEnt(pidAtb.getPos(), "numParametro", 0);
		Procesador.gestorTS.setValorAtributoCad(pidAtb.getPos(), "etiqueta", "main");
		return new Atributos();
	}

	private static Atributos acc9() {
		tsGlobal = false;
		despLocal = 0;
		Procesador.gestorTS.createTSLocal();
		Regla reg = MT_ASINT.getRegla(9);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos idAtb = atb[1];
		Atributos res = new Atributos();
		res.setPos(idAtb.getPos());
		return res;
	}

	private static Atributos acc10() {
		zonaDeclaracion = false;
		return new Atributos();
	}

	private static Atributos acc11() {
		Regla reg = MT_ASINT.getRegla(11);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos bloqueAtb = atb[3];
		if ((bloqueAtb.getTipo()).equals("tipo_error"))
			GestorError.writeError("Error detectado en el desarrollo del cuerpo del Procedure");
		if (!bloqueAtb.getRet().equals("") && !bloqueAtb.getRet().equals("tipo_ok"))
			GestorError.writeError("Procedure con instruccion de retorno no vacio");
		if (bloqueAtb.getExit() > 0)

			GestorError.writeError("Exit fuera de bucle detectado en Procedure");

		destroy(Tabla.LOCAL);
		tsGlobal = true;
		zonaDeclaracion = true;
		return new Atributos();
	}

	private static Atributos acc12() {
		Regla reg = MT_ASINT.getRegla(12);
		Atributos res = new Atributos();
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos pidAtb = atb[3];
		Atributos aAtb = atb[1];
		Procesador.gestorTS.setTipo(pidAtb.getPos(), "procedimiento");
		if (aAtb.getLongs() > 0) {
			String[] tipos = aAtb.getTipo().split(" ");
			String[] parametros = aAtb.getReferencia().split(" ");
			Procesador.gestorTS.setValorAtributoLista(pidAtb.getPos(), "tipoParametros", tipos);
			Procesador.gestorTS.setValorAtributoLista(pidAtb.getPos(), "PasoParametros", parametros);
			Procesador.gestorTS.setValorAtributoEnt(pidAtb.getPos(), "numParametro", aAtb.getLongs());
		} else {
			Procesador.gestorTS.setValorAtributoEnt(pidAtb.getPos(), "numParametro", 0);
		}
		res.setEtiqueta(numEtiq.toString());
		Procesador.gestorTS.setValorAtributoCad(pidAtb.getPos(), "etiqueta", "EtiqProc" + numEtiq++);
		return res;
	}

	private static Atributos acc13() {
		Regla reg = MT_ASINT.getRegla(13);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos bloqueAtb = atb[3];
		Atributos pfidatAtb = atb[11];
		if (bloqueAtb.getTipo().equals("tipo_error"))
			GestorError.writeError("Ha sucedido un error en la función");
		if (!bloqueAtb.getRet().equals(pfidatAtb.getTipo()) && !bloqueAtb.getRet().equals("tipo_ok")) {
			GestorError.writeError("Funcion con retorno invalido");
		}
		if (bloqueAtb.getExit() > 0)
			GestorError.writeError("Exit no puede situarse fuera del bucle");
		destroy(Tabla.LOCAL);
		tsGlobal = true;
		zonaDeclaracion = true;
		return new Atributos();
	}

	private static Atributos acc14() {
		Regla reg = MT_ASINT.getRegla(14);
		Atributos res = new Atributos();
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos pidAtb = atb[7];
		Atributos aAtb = atb[5];
		Atributos tAtb = atb[1];
		Procesador.gestorTS.setTipo(pidAtb.getPos(), "función");
		if (aAtb.getLongs() > 0) {
			String[] tipos = aAtb.getTipo().split(" ");
			String[] parametros = aAtb.getReferencia().split(" ");
			Procesador.gestorTS.setValorAtributoLista(pidAtb.getPos(), "tipoParametros", tipos);
			Procesador.gestorTS.setValorAtributoLista(pidAtb.getPos(), "PasoParametros", parametros);
			Procesador.gestorTS.setValorAtributoEnt(pidAtb.getPos(), "numParametro", aAtb.getLongs());
			Procesador.gestorTS.setValorAtributoCad(pidAtb.getPos(), "tipoRetorno", tAtb.getTipo());
		} else {
			Procesador.gestorTS.setValorAtributoEnt(pidAtb.getPos(), "numParametro", 0);
			Procesador.gestorTS.setValorAtributoCad(pidAtb.getPos(), "tipoRetorno", tAtb.getTipo());
		}
		res.setTipo(tAtb.getTipo());
		res.setEtiqueta(numEtiq.toString());
		Procesador.gestorTS.setValorAtributoCad(pidAtb.getPos(), "etiqueta", "EtiqFunc" + numEtiq++);
		return res;
	}

	// TODO: Modificacion declaracion de variables
	
	private static Atributos acc15() {
		Regla reg = MT_ASINT.getRegla(15);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos idAtb = atb[9];
		Atributos tAtb = atb[5];
		Procesador.gestorTS.setTipo(idAtb.getPos(), tAtb.getTipo());
		if (tsGlobal) {
			Procesador.gestorTS.setValorAtributoEnt(idAtb.getPos(), "desplazamiento", despGlobal);
			despGlobal += tAtb.getAncho();
		} else {
			Procesador.gestorTS.setValorAtributoEnt(idAtb.getPos(), "desplazamiento", despLocal);
			despLocal += tAtb.getAncho();
		}
		return new Atributos();
	}

	private static Atributos acc16() {
		return new Atributos();
	}
		
	
	// TODO: Modificacion declaracion de variables

	private static Atributos acc17() {
		Regla reg = MT_ASINT.getRegla(17);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos idAtb = atb[9];
		Atributos tAtb = atb[5];
		Procesador.gestorTS.setTipo(idAtb.getPos(), tAtb.getTipo());
		if (tsGlobal) {
			Procesador.gestorTS.setValorAtributoEnt(idAtb.getPos(), "desplazamiento", despGlobal);
			despGlobal += tAtb.getAncho();
		} else {
			Procesador.gestorTS.setValorAtributoEnt(idAtb.getPos(), "desplazamiento", despLocal);
			despLocal += tAtb.getAncho();
		}
		return new Atributos();
	}

	private static Atributos acc18() {
		return new Atributos();
	}

	private static Atributos acc19() {
		Atributos res = new Atributos();
		res.setTipo("lógico");
		res.setAncho(1);
		return res;
	}

	private static Atributos acc20() {
		Atributos res = new Atributos();
		res.setTipo("entero");
		res.setAncho(1);
		return res;
	}

	private static Atributos acc21() {
		Atributos res = new Atributos();
		res.setTipo("cadena");
		res.setAncho(64);
		return res;
	}

	private static Atributos acc22() {
		Regla reg = MT_ASINT.getRegla(22);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		Atributos idAtb = atb[9];
		Atributos tAtb = atb[5];
		Atributos xAtb = atb[11];
		Atributos aaAtb = atb[3];
		Procesador.gestorTS.setTipo(idAtb.getPos(), tAtb.getTipo());
		Procesador.gestorTS.setValorAtributoEnt(idAtb.getPos(), "desplazamiento", despLocal);
		if (xAtb.getReferencia().equals("referencia")) {
			Procesador.gestorTS.setValorAtributoEnt(idAtb.getPos(), "modoParametro", 1);
			if (aaAtb.getReferencia() != null) {
				res.setReferencia("referencia " + aaAtb.getReferencia());
			} else {
				res.setReferencia("referencia");
			}
			despLocal += 1;
		} else {
			Procesador.gestorTS.setValorAtributoEnt(idAtb.getPos(), "modoParametro", 0);
			if (aaAtb.getReferencia() != null) {
				res.setReferencia("valor " + aaAtb.getReferencia());
			} else {
				res.setReferencia("valor");
			}
			despLocal += tAtb.getAncho();
		}
		if (!aaAtb.getTipo().equals(""))
			res.setTipo(tAtb.getTipo() + " " + aaAtb.getTipo());
		else
			res.setTipo(tAtb.getTipo());
		res.setLong(1 + aaAtb.getLongs());
		return res;
	}

	private static Atributos acc23() {
		Atributos res = new Atributos();
		res.setTipo("");
		res.setLong(0);
		return res;
	}

	private static Atributos acc24() {
		Regla reg = MT_ASINT.getRegla(24);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		Atributos idAtb = atb[7];
		Atributos tAtb = atb[3];
		Atributos xAtb = atb[9];
		Atributos aaAtb = atb[1];
		Procesador.gestorTS.setTipo(idAtb.getPos(), tAtb.getTipo());
		Procesador.gestorTS.setValorAtributoEnt(idAtb.getPos(), "desplazamiento", despLocal);
		if (xAtb.getReferencia().equals("referencia")) {
			Procesador.gestorTS.setValorAtributoEnt(idAtb.getPos(), "modoParametro", 1);
			if (aaAtb.getReferencia() != null) {
				res.setReferencia("referencia " + aaAtb.getReferencia());
			} else {
				res.setReferencia("referencia");
			}
			despLocal += 1;
		} else {
			Procesador.gestorTS.setValorAtributoEnt(idAtb.getPos(), "modoParametro", 0);
			if (aaAtb.getReferencia() != null) {
				res.setReferencia("valor " + aaAtb.getReferencia());
			} else {
				res.setReferencia("valor");
			}
			despLocal += tAtb.getAncho();
		}
		if (!aaAtb.getTipo().equals(""))
			res.setTipo(tAtb.getTipo() + " " + aaAtb.getTipo());
		else
			res.setTipo(tAtb.getTipo());
		res.setLong(1 + aaAtb.getLongs());
		return res;
	}

	private static Atributos acc25() {
		Atributos res = new Atributos();
		res.setTipo("");
		res.setLong(0);
		return res;
	}

	private static Atributos acc26() {
		Regla reg = MT_ASINT.getRegla(26);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		res.setTipo(atb[3].getTipo());
		res.setExit(atb[3].getExit());
		res.setRet(atb[3].getRet());
		return res;
	}

	private static Atributos acc27() {
		Regla reg = MT_ASINT.getRegla(27);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		Atributos bAtb = atb[3];
		Atributos cAtb = atb[1];
		if (bAtb.getTipo().equals("tipo_ok")) {
			res.setTipo(cAtb.getTipo());
		} else {
			res.setTipo("tipo_error");
		}
		res.setExit(bAtb.getExit() + cAtb.getExit());
		String bRet = bAtb.getRet().length() > 0 ? bAtb.getRet() : "tipo_ok";
		String cRet = cAtb.getRet().length() > 0 ? cAtb.getRet() : "tipo_ok";
		if (bRet.equals(cRet)) {
			res.setRet(bRet);
		} else if (bRet.equals("tipo_ok")) {
			res.setRet(cAtb.getRet());
		} else if (cRet.equals("tipo_ok")) {
			res.setRet(bAtb.getRet());
		} else {
			res.setRet("tipo_error");
			GestorError.writeError("Tipos de retorno de diferentes tipos en un subprograma");
		}
		return res;
	}

	private static Atributos acc28() {
		Atributos res = new Atributos();
		res.setTipo("tipo_ok");
		res.setExit(0);
		res.setRet("tipo_ok");
		return res;
	}
	
	// TODO: Modificar para sentencia condicional sencilla
	
	private static Atributos acc29() {
		Regla reg = MT_ASINT.getRegla(29);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		Atributos eeAtb = atb[5];
		Atributos sAtb = atb[1];
		if (eeAtb.getTipo().equals("lógico"))
			res.setTipo(sAtb.getTipo());
		else {
			res.setTipo("tipo_error");
			GestorError.writeError(
					"La sentencia IF solo acepta expresiones lógica.\n \t Se ha recibido: [" + eeAtb.getTipo() + "]");
		}
		res.setExit(sAtb.getExit());
		res.setRet(sAtb.getRet());
		return res;
	}

	private static Atributos acc30() {
		Regla reg = MT_ASINT.getRegla(30);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos eAtb = atb[1];
		Atributos res = new Atributos();
		res.setTipo(eAtb.getTipo());
		return res;
	}

	private static Atributos acc31() {
		Regla reg = MT_ASINT.getRegla(31);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		res.setTipo(atb[1].getTipo());
		res.setExit(atb[1].getExit());
		res.setRet(atb[1].getRet());
		return res;
	}

	private static Atributos acc32() {
		Regla reg = MT_ASINT.getRegla(32);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		Atributos eeAtb = atb[7];
		Atributos bloqueAtb = atb[3];
		if (eeAtb.getTipo().equals("lógico"))
			res.setTipo(bloqueAtb.getTipo());
		else {
			res.setTipo("tipo_error");
			GestorError.writeError("Sentencia condicional compuesta con condicion no logica");
		}
		res.setExit(bloqueAtb.getExit());
		res.setRet(bloqueAtb.getRet());
		return res;
	}

	private static Atributos acc33() {
		Regla reg = MT_ASINT.getRegla(33);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		Atributos thenAtb = atb[7];
		Atributos bloqueAtb = atb[3];
		if (thenAtb.getTipo().equals("tipo_ok"))
			res.setTipo(bloqueAtb.getTipo());
		else
			res.setTipo("tipo_error");
		res.setExit(thenAtb.getExit() + bloqueAtb.getExit());

		if (thenAtb.getRet().equals(bloqueAtb.getRet()))
			res.setRet(bloqueAtb.getRet());
		else if (thenAtb.getRet().equals("tipo_ok"))
			res.setRet(bloqueAtb.getRet());
		else if (bloqueAtb.getRet().equals("tipo_ok"))
			res.setRet(thenAtb.getRet());
		else {
			res.setRet("tipo_error");
			GestorError.writeError("Instrucciones de retorno de diferentes tipos en sentencia condicional compuesta");
		}
		return res;
	}

	private static Atributos acc34() {
		Regla reg = MT_ASINT.getRegla(34);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		Atributos eeATB = atb[7];
		Atributos bloqueATB = atb[3];
		if (eeATB.getTipo().equals("lógico")) {
			res.setTipo(bloqueATB.getTipo());
		} else {
			GestorError.setError(Acciones.eSem2_tipo_incompatible,
					"tipo recibido: " + eeATB + "; tipo esperado: Lógico");
			res.setTipo("tipo_error");
		}
		res.setExit(bloqueATB.getExit());
		res.setRet(bloqueATB.getRet());
		return res;
	}

	private static Atributos acc35() {
		Regla reg = MT_ASINT.getRegla(35);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		Atributos eeATB = atb[7];
		Atributos bloqueATB = atb[3];
		if (eeATB.getTipo().equals("lógico")) {
			res.setTipo(bloqueATB.getTipo());
		} else {
			GestorError.setError(Acciones.eSem2_tipo_incompatible,
					"tipo recibido: " + eeATB + "; tipo esperado: Lógico");
			res.setTipo("tipo_error");
		}
		res.setExit(bloqueATB.getExit());
		res.setRet(bloqueATB.getRet());
		return res;
	}

	private static Atributos acc36() {
		return new Atributos();
	}
	
	// TODO: Modificar la sentencia especifica de la practica, en este caso repetititva REPEAT-UNTIL

	private static Atributos acc37() {
		Regla reg = MT_ASINT.getRegla(37);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		Atributos eATB = atb[3];
		Atributos cATB = atb[7];
		if (eATB.getTipo().equals("lógico")) {
			res.setTipo(cATB.getTipo());
		} else {
			GestorError.setError(Acciones.eSem2_tipo_incompatible,
					"tipo recibido: " + eATB + "; tipo esperado: Lógico");
			res.setTipo("tipo_error");
		}
		res.setExit(cATB.getExit());
		res.setRet(cATB.getRet());
		return res;
	}

	private static Atributos acc38() {
		Regla reg = MT_ASINT.getRegla(38);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		Atributos cATB = atb[5];
		res.setTipo(cATB.getTipo());
		res.setExit(0);
		if (cATB.getExit() != 1) {
			GestorError.setError(Acciones.eSem1_loop, "");
		}
		res.setRet(cATB.getRet());
		return res;
	}

	private static Atributos acc39() {
		Regla reg = MT_ASINT.getRegla(39);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		Atributos forATB = atb[7];
		Atributos bloqueATB = atb[3];
		if (forATB.getTipo().equals("tipo_ok")) {
			res.setTipo(bloqueATB.getTipo());
		} else {
			GestorError.setError(Acciones.eSem4_for_error, "");
			res.setTipo("tipo_error");
		}
		res.setExit(bloqueATB.getExit());
		res.setRet(bloqueATB.getRet());
		return res;
	}

	private static Atributos acc40() {
		Regla reg = MT_ASINT.getRegla(40);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		Atributos idATB = atb[9];
		Atributos e1ATB = atb[5];
		Atributos e2ATB = atb[1];
		String idTipo = Procesador.gestorTS.getTipo(idATB.getPos());
		if (e1ATB.getTipo().equals(e2ATB.getTipo()) && idTipo.equals(e1ATB.getTipo())
				&& e1ATB.getTipo().equals("entero")) {
			res.setTipo("tipo_ok");
		} else {
			GestorError.setError(Acciones.eSem2_tipo_incompatible, "for [Entero] := [Entero] to [Entero]... "
					+ "pero se ha recibido " + idTipo + ", " + e1ATB.getTipo() + ", " + e2ATB.getTipo());
			res.setTipo("tipo_error");
		}
		return res;
	}

	private static Atributos acc41() {
		Regla reg = MT_ASINT.getRegla(41);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		Atributos expATB = atb[11];
		Atributos nATB = atb[7];
		Atributos oATB = atb[5];
		if (expATB.getTipo().equals("entero") && nATB.getTipo().equals(oATB.getTipo())
				&& oATB.getTipo().equals("tipo_ok")) {
			res.setTipo("tipo_ok");
		} else {
			if (oATB.getTipo().equals("tipo_ok")) {
				GestorError.setError(Acciones.eSem2_tipo_incompatible,
						"case [Entero] of... pero se ha recibido " + expATB.getTipo());
			} else {
				GestorError.setError(Acciones.eSem5_case_otherwise_error, "");
			}
			res.setTipo("tipo_error");
		}
		res.setExit(nATB.getExit() + oATB.getExit());
		if (nATB.getRet().equals(oATB.getRet()) || oATB.getRet().equals("tipo_ok")) {
			res.setRet(nATB.getRet());
		} else if (nATB.getRet().equals("tipo_ok")) {
			res.setRet(oATB.getRet());
		} else {
			res.setRet("tipo_error");
		}
		if (nATB.getRet().equals("tipo_error")) {
			GestorError.setError(Acciones.eSem5_case_otherwise_error, "");
		}
		return res;
	}

	private static Atributos acc42() {
		Regla reg = MT_ASINT.getRegla(42);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos eATB = atb[1];
		Atributos res = new Atributos();
		res.setTipo(eATB.getTipo());
		return res;
	}

	private static Atributos acc43() {
		Regla reg = MT_ASINT.getRegla(43);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos nATB = atb[9];
		Atributos bloqueATB = atb[3];
		Atributos res = new Atributos();
		if (nATB.getTipo().equals("tipo_ok")) {
			res.setTipo(bloqueATB.getTipo());
		} else {
			GestorError.setError(Acciones.eSem6_case_error, "");
			res.setTipo("tipo_error");
		}
		res.setExit(nATB.getExit() + bloqueATB.getExit());
		if (nATB.getRet().equals(bloqueATB.getRet()) || nATB.getRet().equals("tipo_ok")) {
			res.setRet(bloqueATB.getRet());
		} else if (bloqueATB.getRet().equals("tipo_ok")) {
			res.setRet(nATB.getRet());
		} else {
			res.setRet("tipo_error");
		}
		if (nATB.getRet().equals("tipo_error")) {
			GestorError.setError(Acciones.eSem7_case_bloque_error, "");
		}
		return res;
	}

	private static Atributos acc44() {
		return new Atributos();
	}

	private static Atributos acc45() {
		Atributos res = new Atributos();
		res.setTipo("tipo_ok");
		res.setExit(0);
		res.setRet("tipo_ok");
		return res;
	}

	private static Atributos acc46() {
		Regla reg = MT_ASINT.getRegla(46);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos bloqueATB = atb[3];
		Atributos res = new Atributos();
		res.setTipo(bloqueATB.getTipo());
		res.setExit(bloqueATB.getExit());
		res.setRet(bloqueATB.getRet());
		return res;
	}

	private static Atributos acc47() {
		Atributos res = new Atributos();
		res.setTipo("tipo_ok");
		res.setExit(0);
		res.setRet("tipo_ok");
		return res;
	}

	
	// TODO: Modificar sentencia de salida
	
	private static Atributos acc48() {
		Regla reg = MT_ASINT.getRegla(48);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		Atributos llATB = atb[3];
		res.setTipo("tipo_ok");
		if (llATB.getTipo() != null) {
			String[] tipos = llATB.getTipo().split("\\s+");
			if (!(tipos.length == 1 && tipos[0].equals(""))) {
				for (int i = 0; i < tipos.length; i++) {
					String tipo = tipos[i];
					if (!tipo.equals("entero") && !tipo.equals("cadena")) {
						GestorError.setError(Acciones.eSem2_tipo_incompatible,
								"la sentencia WRITE solo acepta entero o cadena, " + "pero ha recibido " + tipo);
						res.setTipo("tipo_error");
						break;
					}
				}
			}
		}
		res.setExit(0);
		res.setRet("tipo_ok");
		return res;
	}
	
	// TODO: Modificar sentencia de salida
	
	private static Atributos acc49() {
		Regla reg = MT_ASINT.getRegla(49);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		Atributos llATB = atb[3];
		res.setTipo("tipo_ok");
		if (llATB.getTipo() != null) {
			String[] tipos = llATB.getTipo().split("\\s+");
			if (!(tipos.length == 1 && tipos[0].equals(""))) {
				for (int i = 0; i < tipos.length; i++) {
					String tipo = tipos[i];
					if (!tipo.equals("entero") && !tipo.equals("cadena")) {
						GestorError.setError(Acciones.eSem2_tipo_incompatible,
								"la sentencia WRITELN solo acepta entero o cadena, " + "pero ha recibido" + tipo);
						res.setTipo("tipo_error");
						break;
					}
				}
			}
		}
		res.setExit(0);
		res.setRet("tipo_ok");
		return res;
	}
	
	
	// TODO: Modificar sentencia de entrada
	
	private static Atributos acc50() {
		Regla reg = MT_ASINT.getRegla(50);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		Atributos vATB = atb[5];
		res.setTipo("tipo_ok");
		String[] tipos = vATB.getTipo().split("\\s+");
		for (int i = 0; i < tipos.length; i++) {
			String tipo = tipos[i];
			if (!tipo.equals("entero") && !tipo.equals("cadena")) {
				GestorError.setError(Acciones.eSem2_tipo_incompatible,
						"la sentencia READ solo acepta entero o cadena, " + "pero ha recibido " + tipo);
				res.setTipo("tipo_error");
				break;
			}
		}
		res.setExit(0);
		res.setRet("tipo_ok");
		return res;
	}

	
	// TODO: Modificar este metodo para la sentencia de asignacion
	// En este caso se observa que no hay conversion de tipos.
	
	private static Atributos acc51() {
		Regla reg = MT_ASINT.getRegla(51);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		
		String eTipo = atb[3].getTipo();
		Integer idPos = atb[7].getPos();
		String idTipo = Procesador.gestorTS.getTipo(idPos);
		
		// Caso correcto: los tipos son compatibles y sin error
		if (idTipo.equals(eTipo) && !eTipo.equals("tipo_error")) {
			
			
			// obtener el desplazamiento del identificador
			
			int desp = Procesador.gestorTS.getValorAtributoEnt(idPos, "desplazamiento");
			
			boolean esGlobal = desp < despLocal;
			
			String ambito = esGlobal ? "VAR_GLOBAL" : "VAR_LOCAL";
			
			
			// El lugar de la expresión puede ser:
			// - Un temporal si viene de una expresión
			// - Una dirección si es una variable
			// - Un valor directo si es una constante
			
			// String exprLugar = atb[3].getLugar();
			

			// GCI.addCuarteto("ASIG", exprEtiqueta, "-", idEtiqueta); // arreglar
			
			
			Atributos res = new Atributos();
			res.setTipo("tipo_ok");
			res.setExit(0);
			res.setRet("tipo_ok");
			return res;
		} else { 
			// en las ramas de error, se mantiene el manejo existente. No se generar cuartetos si falla
			if (idTipo.equals("función") && eTipo.equals("función")) {
				GestorError.writeError("Las funciones no pueden ser asignados a otra función");
			} else if (idTipo.equals("función")) {
				GestorError.writeError("No se puede asignar un valor a una función");
			} else if (eTipo.equals("función")) {
				GestorError.writeError("Una variable no puede contener una función");
			} else if (idTipo.equals("procedimiento")) {
				String idEtiq = Procesador.gestorTS.getValorAtributoCad(idPos, "etiqueta");

				if (idEtiq.equals("main")) {
					GestorError.writeError("No se permite una asignaciones al programa principal");
				} else {
					GestorError.writeError("No se puede asignar un valor a un procedimiento");
				}
			} else {
				GestorError.setError(Acciones.eSem2_tipo_incompatible,
						Procesador.gestorTS.getTipo(idPos) + " no es compatible con " + eTipo);
			}

			Atributos res = new Atributos();
			res.setTipo("tipo_error");
			res.setExit(0);
			res.setRet("tipo_ok");
			return res;
		}
	}

	
	// TODO: modificar llamada a subprograma(procedimiento)
	
	private static Atributos acc52() {
		Regla reg = MT_ASINT.getRegla(52);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		Atributos idATB = atb[5];
		Atributos llATB = atb[3];
		String idTipo = Procesador.gestorTS.getTipo(idATB.getPos());

		if (llATB.getLongs() > 0 || idTipo.equals("procedimiento")) {
			String idEtiq = Procesador.gestorTS.getValorAtributoCad(idATB.getPos(), "etiqueta");

			if (idEtiq.equals("main")) {
				GestorError.writeError("El programa principal no se puede llamar");
				res.setTipo("tipo_error");
			} else {
				String[] llAtributos = llATB.getTipo().split(" ");

				int numParam = Procesador.gestorTS.getValorAtributoEnt(idATB.getPos(), "numParametro");
				if (numParam > 0) {
					String[] idAtbAtributos = Procesador.gestorTS.getValorAtributoLista(idATB.getPos(),
							"tipoParametros");
					if (llAtributos.length == idAtbAtributos.length && Arrays.compare(llAtributos, idAtbAtributos) == 0
					/*
					 * && Procesador.gestorTS.getValorAtributoCad(idATB.getPos(), "tipoRetorno") ==
					 * null
					 */) {
						res.setTipo("tipo_ok");
					} else {
						GestorError.writeError("Los parametros del procedimiento no coinciden"
								+ "\n \t Se ha recibido: " + Arrays.toString(llAtributos)
								+ "\n \t pero deberia recibir: " + Arrays.toString(idAtbAtributos));
						res.setTipo("tipo_error");
					}
				} else {
					res.setTipo("tipo_ok");
				}
			}
		} else {
			if ((Procesador.gestorTS.getValorAtributoEnt(idATB.getPos(), "numParametro") == 0
					&& Procesador.gestorTS.getValorAtributoCad(idATB.getPos(), "tipoRetorno") == null)) {
				res.setTipo("tipo_ok");
			} else {
				GestorError.setError(Acciones.eSem8_funcion_parametro_incompatible_error, "");
				res.setTipo("tipo_error");
			}
		}
		res.setExit(0);
		res.setRet("tipo_ok");
		return res;
	}

	
	// TODO: Modificar sentencia de retorno
	
	private static Atributos acc53() {
		Regla reg = MT_ASINT.getRegla(53);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		Atributos yATB = atb[3];
		if (!yATB.getTipo().equals("tipo_error")) {
			res.setTipo("tipo_ok");
		} else {
			GestorError.setError(Acciones.eSem9_retorno_funcion_error, "");
			res.setTipo("tipo_error");
		}
		res.setExit(0);
		res.setRet(yATB.getTipo());
		return res;
	}

	private static Atributos acc54() {
		Regla reg = MT_ASINT.getRegla(54);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		Atributos eATB = atb[3];
		if (eATB.getTipo().equals("lógico")) {
			res.setTipo("tipo_ok");
		} else {
			GestorError.setError(Acciones.eSem10_expresion_error, "");
			res.setTipo("tipo_error");
		}
		res.setExit(1);
		res.setRet("tipo_ok");
		return res;
	}

	private static Atributos acc55() {
		Regla reg = MT_ASINT.getRegla(55);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos lATB = atb[3];
		Atributos res = new Atributos();
		res.setTipo(lATB.getTipo());
		res.setLong(lATB.getLongs());
		return res;
	}

	private static Atributos acc56() {
		Atributos res = new Atributos();
		res.setTipo("");
		res.setLong(0);
		return res;
	}

	private static Atributos acc57() {
		Regla reg = MT_ASINT.getRegla(57);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		Atributos qATB = atb[1];
		Atributos eATB = atb[3];
		if (qATB.getTipo().equals("")) {
			res.setTipo(eATB.getTipo());
		} else {
			res.setTipo(eATB.getTipo() + " " + qATB.getTipo());
		}
		res.setLong(1 + qATB.getLongs());
		return res;
	}

	private static Atributos acc58() {
		Regla reg = MT_ASINT.getRegla(58);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();

		Atributos q1ATB = atb[1];
		Atributos eATB = atb[3];

		if (q1ATB.getTipo().equals("")) {
			res.setTipo(eATB.getTipo());
		} else {
			res.setTipo(eATB.getTipo() + " " + q1ATB.getTipo());
		}
		res.setLong(1 + q1ATB.getLongs());
		return res;
	}

	private static Atributos acc59() {
		Atributos res = new Atributos();
		res.setTipo("");
		res.setLong(0);
		return res;
	}

	private static Atributos acc60() {
		Regla reg = MT_ASINT.getRegla(60);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		Atributos wATB = atb[1];
		Atributos idATB = atb[3];

		if (wATB.getTipo().equals("")) {
			res.setTipo(Procesador.gestorTS.getTipo(idATB.getPos()));
		} else {
			res.setTipo(Procesador.gestorTS.getTipo(idATB.getPos()) + " " + wATB.getTipo());
		}
		res.setLong(1 + wATB.getLongs());
		return res;
	}

	private static Atributos acc61() {
		Regla reg = MT_ASINT.getRegla(61);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		Atributos w1ATB = atb[1];
		Atributos idATB = atb[3];

		if (w1ATB.getTipo().equals("")) {
			res.setTipo(Procesador.gestorTS.getTipo(idATB.getPos()));
		} else {
			res.setTipo(Procesador.gestorTS.getTipo(idATB.getPos()) + " " + w1ATB.getTipo());
		}
		res.setLong(1 + w1ATB.getLongs());
		return res;
	}

	private static Atributos acc62() {
		Atributos res = new Atributos();
		res.setTipo("");
		res.setLong(0);
		return res;
	}

	private static Atributos acc63() {
		Regla reg = MT_ASINT.getRegla(63);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos eATB = atb[1];
		Atributos res = new Atributos();
		res.setTipo(eATB.getTipo());
		return res;
	}

	private static Atributos acc64() {
		Atributos res = new Atributos();
		res.setTipo("");
		return res;
	}

	private static Atributos acc65() {
		Regla reg = MT_ASINT.getRegla(65);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		Atributos e1ATB = atb[5];
		Atributos fATB = atb[1];

		if (e1ATB.getTipo().equals(fATB.getTipo()) && e1ATB.getTipo().equals("lógico")) {
			res.setTipo("lógico");
		} else {
			GestorError.setError(Acciones.eSem2_tipo_incompatible,
					"tipo actual: " + e1ATB.getTipo() + " y " + fATB.getTipo() + "; tipo esperado: lógico y lógico");
			res.setTipo("tipo_error");
		}
		return res;
	}

	private static Atributos acc66() {
		Regla reg = MT_ASINT.getRegla(66);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		Atributos e1ATB = atb[5];
		Atributos fATB = atb[1];

		if (e1ATB.getTipo().equals(fATB.getTipo()) && e1ATB.getTipo().equals("lógico")) {
			res.setTipo("lógico");
		} else {
			GestorError.setError(Acciones.eSem2_tipo_incompatible,
					"tipo actual: " + e1ATB.getTipo() + " y " + fATB.getTipo() + "; tipo esperado: lógico y lógico");

			res.setTipo("tipo_error");
		}
		return res;
	}

	private static Atributos acc67() {
		Regla reg = MT_ASINT.getRegla(67);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		res.setTipo(atb[1].getTipo());
		return res;
	}

	private static Atributos acc68() {
		Regla reg = MT_ASINT.getRegla(68);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		Atributos f1ATB = atb[5];
		Atributos gATB = atb[1];
		if (f1ATB.getTipo().equals(gATB.getTipo()) && f1ATB.getTipo().equals("lógico")) {
			res.setTipo("lógico");
		} else {
			GestorError.setError(Acciones.eSem2_tipo_incompatible,
					"tipo actual: " + f1ATB.getTipo() + " y " + gATB.getTipo() + "; tipo esperado: lógico y lógico");

			res.setTipo("tipo_error");
		}
		return res;
	}

	private static Atributos acc69() {
		Regla reg = MT_ASINT.getRegla(69);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos gATB = atb[1];
		Atributos res = new Atributos();
		res.setTipo(gATB.getTipo());
		return res;
	}

	private static Atributos acc70() {
		Regla reg = MT_ASINT.getRegla(70);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		String gTipo = atb[5].getTipo();
		String hTipo = atb[1].getTipo();
		if (gTipo.equals(hTipo) && hTipo.equals("entero")) {
			Atributos res = new Atributos();
			res.setTipo("lógico");
			return res;
		} else {
			GestorError.setError(Acciones.eSem2_tipo_incompatible,
					"tipo actual: " + gTipo + " y " + hTipo + "; tipo esperado: entero y entero");
			Atributos res = new Atributos();
			res.setTipo("tipo_error");
			return res;
		}
	}

	private static Atributos acc71() {
		Regla reg = MT_ASINT.getRegla(71);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		String gTipo = atb[5].getTipo();
		String hTipo = atb[1].getTipo();
		if (gTipo.equals(hTipo) && hTipo.equals("entero")) {
			Atributos res = new Atributos();
			res.setTipo("lógico");
			return res;
		} else {
			GestorError.setError(Acciones.eSem2_tipo_incompatible,
					"tipo actual: " + gTipo + " y " + hTipo + "; tipo esperado: entero y entero");
			Atributos res = new Atributos();
			res.setTipo("tipo_error");
			return res;
		}
	}

	private static Atributos acc72() {
		Regla reg = MT_ASINT.getRegla(72);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		String gTipo = atb[5].getTipo();
		String hTipo = atb[1].getTipo();
		if (gTipo.equals(hTipo) && hTipo.equals("entero")) {
			Atributos res = new Atributos();
			res.setTipo("lógico");
			return res;
		} else {
			GestorError.setError(Acciones.eSem2_tipo_incompatible,
					"tipo actual: " + gTipo + " y " + hTipo + "; tipo esperado: entero y entero");
			Atributos res = new Atributos();
			res.setTipo("tipo_error");
			return res;
		}
	}

	private static Atributos acc73() {
		Regla reg = MT_ASINT.getRegla(73);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		String gTipo = atb[5].getTipo();
		String hTipo = atb[1].getTipo();
		if (gTipo.equals(hTipo) && hTipo.equals("entero")) {
			Atributos res = new Atributos();
			res.setTipo("lógico");
			return res;
		} else {
			GestorError.setError(Acciones.eSem2_tipo_incompatible,
					"tipo actual: " + gTipo + " y " + hTipo + "; tipo esperado: entero y entero");
			Atributos res = new Atributos();
			res.setTipo("tipo_error");
			return res;
		}
	}

	private static Atributos acc74() {
		Regla reg = MT_ASINT.getRegla(74);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		String gTipo = atb[5].getTipo();
		String hTipo = atb[1].getTipo();
		if (gTipo.equals(hTipo) && hTipo.equals("entero")) {
			Atributos res = new Atributos();
			res.setTipo("lógico");
			return res;
		} else {
			GestorError.setError(Acciones.eSem2_tipo_incompatible,
					"tipo actual: " + gTipo + " y " + hTipo + "; tipo esperado: entero y entero");
			Atributos res = new Atributos();
			res.setTipo("tipo_error");
			return res;
		}
	}

	private static Atributos acc75() {
		Regla reg = MT_ASINT.getRegla(75);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		String gTipo = atb[5].getTipo();
		String hTipo = atb[1].getTipo();
		if (gTipo.equals(hTipo) && hTipo.equals("entero")) {
			Atributos res = new Atributos();
			res.setTipo("lógico");
			return res;
		} else {
			GestorError.setError(Acciones.eSem2_tipo_incompatible,
					"tipo actual: " + gTipo + " y " + hTipo + "; tipo esperado: entero y entero");
			Atributos res = new Atributos();
			res.setTipo("tipo_error");
			return res;
		}
	}

	private static Atributos acc76() {
		Regla reg = MT_ASINT.getRegla(76);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		res.setTipo(atb[1].getTipo());
		return res;
	}

	private static Atributos acc77() {
		Regla reg = MT_ASINT.getRegla(77);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		String iTipo = atb[1].getTipo();
		String h1Tipo = atb[5].getTipo();
		if (iTipo.equals(h1Tipo) && (iTipo.equals("entero") || iTipo.equals("cadena"))) {
			Atributos res = new Atributos();
			res.setTipo(h1Tipo);
			return res;
		} else {
			GestorError.setError(Acciones.eSem2_tipo_incompatible,
					"tipo actual: " + iTipo + " y " + h1Tipo + "; tipo esperado: entero y entero o cadena y cadena");
			Atributos res = new Atributos();
			res.setTipo("tipo_error");
			return res;
		}
	}

	private static Atributos acc78() {
		Regla reg = MT_ASINT.getRegla(78);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		String hTipo = atb[5].getTipo();
		String iTipo = atb[1].getTipo();
		if (hTipo.equals(iTipo) && iTipo.equals("entero")) {
			Atributos res = new Atributos();
			res.setTipo("entero");
			return res;
		} else {
			GestorError.setError(Acciones.eSem2_tipo_incompatible,
					"tipo actual: " + hTipo + " y " + iTipo + "; tipo esperado: entero y entero");
			Atributos res = new Atributos();
			res.setTipo("tipo_error");
			return res;
		}
	}

	private static Atributos acc79() {
		Regla reg = MT_ASINT.getRegla(79);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		res.setTipo(atb[1].getTipo());
		return res;
	}

	private static Atributos acc80() {
		Regla reg = MT_ASINT.getRegla(80);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		String iTipo = atb[5].getTipo();
		String jTipo = atb[1].getTipo();
		if (iTipo.equals(jTipo) && jTipo.equals("entero")) {
			Atributos res = new Atributos();
			res.setTipo("entero");
			return res;
		} else {
			GestorError.setError(Acciones.eSem2_tipo_incompatible,
					"tipo actual: " + iTipo + " y " + jTipo + "; tipo esperado: entero y entero");
			Atributos res = new Atributos();
			res.setTipo("tipo_error");
			return res;
		}
	}

	private static Atributos acc81() {
		Regla reg = MT_ASINT.getRegla(81);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		String iTipo = atb[5].getTipo();
		String jTipo = atb[1].getTipo();
		if (iTipo.equals(jTipo) && jTipo.equals("entero")) {
			Atributos res = new Atributos();
			res.setTipo("entero");
			return res;
		} else {
			GestorError.setError(Acciones.eSem2_tipo_incompatible,
					"tipo actual: " + iTipo + " y " + jTipo + "; tipo esperado: entero y entero");
			Atributos res = new Atributos();
			res.setTipo("tipo_error");
			return res;
		}
	}

	private static Atributos acc82() {
		Regla reg = MT_ASINT.getRegla(82);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		String iTipo = atb[5].getTipo();
		String jTipo = atb[1].getTipo();
		if (iTipo.equals(jTipo) && jTipo.equals("entero")) {
			Atributos res = new Atributos();
			res.setTipo("entero");
			return res;
		} else {
			GestorError.setError(Acciones.eSem2_tipo_incompatible,
					"tipo actual: " + iTipo + " y " + jTipo + "; tipo esperado: entero y entero");
			Atributos res = new Atributos();
			res.setTipo("tipo_error");
			return res;
		}
	}

	private static Atributos acc83() {
		Regla reg = MT_ASINT.getRegla(83);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		res.setTipo(atb[1].getTipo());
		return res;
	}

	private static Atributos acc84() {
		Regla reg = MT_ASINT.getRegla(84);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		String kTipo = atb[1].getTipo();
		String jTipo = atb[5].getTipo();
		if (kTipo.equals(jTipo) && kTipo.equals("entero")) {
			Atributos res = new Atributos();
			res.setTipo("entero");
			return res;
		} else {
			GestorError.setError(Acciones.eSem2_tipo_incompatible,
					"tipo actual: " + kTipo + " y " + jTipo + "; tipo esperado: entero y entero");
			Atributos res = new Atributos();
			res.setTipo("tipo_error");
			return res;
		}
	}

	private static Atributos acc85() {
		Regla reg = MT_ASINT.getRegla(85);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		res.setTipo(atb[1].getTipo());
		return res;
	}

	private static Atributos acc86() {
		Regla reg = MT_ASINT.getRegla(86);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		if (atb[1].getTipo().equals("lógico")) {
			Atributos res = new Atributos();
			res.setTipo("lógico");
			return res;
		} else {
			GestorError.setError(Acciones.eSem2_tipo_incompatible,
					"tipo actual: " + atb[1].getTipo() + "; tipo esperado: lógico");
			Atributos res = new Atributos();
			res.setTipo("tipo_error");
			return res;
		}
	}

	private static Atributos acc87() {
		Regla reg = MT_ASINT.getRegla(87);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		if (atb[1].getTipo().equals("entero")) {
			Atributos res = new Atributos();
			res.setTipo("entero");
			return res;
		} else {
			GestorError.setError(Acciones.eSem2_tipo_incompatible,
					"tipo actual: " + atb[1].getTipo() + "; tipo esperado: entero");
			Atributos res = new Atributos();
			res.setTipo("tipo_error");
			return res;
		}
	}

	private static Atributos acc88() {
		Regla reg = MT_ASINT.getRegla(88);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		if (atb[1].getTipo().equals("entero")) {
			Atributos res = new Atributos();
			res.setTipo("entero");
			return res;
		} else {
			GestorError.setError(Acciones.eSem2_tipo_incompatible,
					"tipo actual: " + atb[1].getTipo() + "; tipo esperado: entero");
			Atributos res = new Atributos();
			res.setTipo("tipo_error");
			return res;
		}
	}

	private static Atributos acc89() {
		Regla reg = MT_ASINT.getRegla(89);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		res.setTipo(atb[1].getTipo());
		return res;
	}
	
	// las acciones 90-93 establecen los tipos para constantes
	// en principio no se necesitan realizar cambios
	
	
	private static Atributos acc90() {
		Atributos res = new Atributos();
		res.setTipo("entero");
		return res;
	}

	private static Atributos acc91() {
		Atributos res = new Atributos();
		res.setTipo("cadena");
		return res;
	}

	private static Atributos acc92() {
		Atributos res = new Atributos();
		res.setTipo("lógico");
		return res;
	}

	private static Atributos acc93() {
		Atributos res = new Atributos();
		res.setTipo("lógico");
		return res;
	}

	
	// TODO: MOdificar para procedimiento
	
	private static Atributos acc94() {
		Regla reg = MT_ASINT.getRegla(94);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		Atributos llAtb = atb[1];
		Atributos idAtb = atb[3];
		String idTipo = Procesador.gestorTS.getTipo(idAtb.getPos());

		if (idTipo.equals("función")) {
			String t = Procesador.gestorTS.getValorAtributoCad(idAtb.getPos(), "tipoRetorno");
			int numParam = Procesador.gestorTS.getValorAtributoEnt(idAtb.getPos(), "numParametro");

			if (numParam > 0) {
				String[] llAtributos = llAtb.getTipo().split(" ");
				String[] idAtbAtributos = Procesador.gestorTS.getValorAtributoLista(idAtb.getPos(), "tipoParametros");
				if (llAtributos.length == idAtbAtributos.length && Arrays.compare(llAtributos, idAtbAtributos) == 0) {
					res.setTipo(t);
				} else {
					GestorError.writeError("Los parametros de la función no coinciden" + "\n \t Se ha recibido: "
							+ Arrays.toString(llAtributos) + "\n \t pero deberia recibir: "
							+ Arrays.toString(idAtbAtributos));
					res.setTipo("tipo_error");
				}
			} else {
				res.setTipo(t);
			}
		} else if (idTipo.equals("procedimiento")) {
			String idEtiq = Procesador.gestorTS.getValorAtributoCad(idAtb.getPos(), "etiqueta");

			if (idEtiq.equals("main")) {
				GestorError.writeError("LLamada ilegal al programa principal");
			} else {
				GestorError.writeError("Un procedimiento no devuelve nada");
				int numParam = Procesador.gestorTS.getValorAtributoEnt(idAtb.getPos(), "numParametro");
				if (numParam > 0) {
					String[] llAtributos = llAtb.getTipo().split(" ");
					String[] idAtbAtributos = Procesador.gestorTS.getValorAtributoLista(idAtb.getPos(),
							"tipoParametros");
					if (llAtributos.length != idAtbAtributos.length
							|| Arrays.compare(llAtributos, idAtbAtributos) == 0) {
						GestorError.writeError("Los parametros del procedimiento no coinciden"
								+ "\n \t Se ha recibido: " + Arrays.toString(llAtributos)
								+ "\n \t pero deberia recibir: " + Arrays.toString(idAtbAtributos));
					}
				}
			}
			res.setTipo("tipo_error");
		} else if (idTipo.equalsIgnoreCase("entero") || idTipo.equalsIgnoreCase("lógico")
				|| idTipo.equalsIgnoreCase("cadena") && llAtb.getTipo().equals("")) {
			res.setTipo(idTipo);
		} else {
			GestorError.setError(Acciones.eSem2_tipo_incompatible,
					"tipo actual: " + idTipo + "; tipo esperado: {entero, lógico, cadena}");
			res.setTipo("tipo_error");
		}
		return res;
	}

	private static Atributos acc95() {
		Regla reg = MT_ASINT.getRegla(95);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		res.setTipo(atb[3].getTipo());
		return res;
	}

	private static Atributos acc96() {
		Regla reg = MT_ASINT.getRegla(96);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		Atributos lAtb = atb[3];
		Atributos zAtb = atb[9];
		if (zAtb.getTipo().equals("entero")) {
			res.setTipo("lógico");
		} else {
			res.setTipo("tipo_error");
			GestorError.setError(Acciones.eSem2_tipo_incompatible,
					"tipo actual: " + zAtb.getTipo() + "; tipo esperado: entero");
			return res;
		}

		String[] tipos = lAtb.getTipo().split("\\s+");
		for (int i = 0; i < tipos.length; i++) {
			String tipo = tipos[i];
			if (!tipo.equals("entero")) {
				res.setTipo("tipo_error");
				GestorError.setError(Acciones.eSem2_tipo_incompatible,
						"tipo actual: " + tipo + "; tipo esperado: entero");
				break;
			}
		}
		return res;
	}
	
	// TODO: Modificar operador especial, especifico del grupo, el operador MAX

	private static Atributos acc97() {
		Regla reg = MT_ASINT.getRegla(97);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		Atributos lAtb = atb[3];
		res.setTipo("entero");
		String[] tipos = lAtb.getTipo().split("\\s+");
		for (int i = 0; i < tipos.length; i++) {
			String tipo = tipos[i];
			if (!tipo.equals("entero")) {
				res.setTipo("tipo_error");
				GestorError.setError(Acciones.eSem2_tipo_incompatible,
						"tipo actual: " + tipo + "; tipo esperado: entero");
				break;
			}
		}
		return res;
	}

	private static Atributos acc98() {
		Regla reg = MT_ASINT.getRegla(98);
		Atributos[] atb = ASin.pilaSem.toArray(new Atributos[reg.numElementos * 2]);
		Atributos res = new Atributos();
		Atributos lAtb = atb[3];
		res.setTipo("entero");
		String[] tipos = lAtb.getTipo().split("\\s+");
		for (int i = 0; i < tipos.length; i++) {
			String tipo = tipos[i];
			if (!tipo.equals("entero")) {
				res.setTipo("tipo_error");
				GestorError.setError(Acciones.eSem2_tipo_incompatible,
						"tipo actual: " + tipo + "; tipo esperado: entero");
				break;
			}
		}
		return res;
	}
	
	// Determinan el modo de los paraemtros, por ref o por valor
	// En principio no se necesitan cambios

	private static Atributos acc99() {
		Atributos res = new Atributos();
		res.setReferencia("referencia");
		return res;
	}

	private static Atributos acc100() {
		Atributos res = new Atributos();
		res.setReferencia("valor");
		return res;
	}
	// ***************************************************************************************************************
	// Fin de la implementacion de los analisis semanticos: acc1 - acc100
}
