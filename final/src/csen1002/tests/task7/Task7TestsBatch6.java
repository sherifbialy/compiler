package csen1002.tests.task7;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

import csen1002.main.task7.CfgLl1Parser;

@Timeout(value = 5, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
public class Task7TestsBatch6 {

	@Test
	public void testCfg1String1() {
		CfgLl1Parser cfgLl1Parser= new CfgLl1Parser("S;J;X;F;G#c;i;p;s;z#S/c,sJ;J/pScX,i,e;X/zGiG,iGs,e;F/p,cGGi;G/pFF,sG#S/c,s;J/p,i,e;X/z,i,e;F/p,c;G/p,s#S/$c;J/$c;X/$c;F/cp;G/ips");
		assertEquals("S;sJ;spScX;spsJcX;spscX;spsciGs;spscipFFs;spscippFs;spscippps", cfgLl1Parser.parse("spscippps"));
	}

	@Test
	public void testCfg1String2() {
		CfgLl1Parser cfgLl1Parser= new CfgLl1Parser("S;J;X;F;G#c;i;p;s;z#S/c,sJ;J/pScX,i,e;X/zGiG,iGs,e;F/p,cGGi;G/pFF,sG#S/c,s;J/p,i,e;X/z,i,e;F/p,c;G/p,s#S/$c;J/$c;X/$c;F/cp;G/ips");
		assertEquals("S;sJ;spScX;spsJcX;spsicX;spsiciGs;spsicisGs;spsicispFFs;spsicisppFs;spsicisppps", cfgLl1Parser.parse("spsicisppps"));
	}

	@Test
	public void testCfg1String3() {
		CfgLl1Parser cfgLl1Parser= new CfgLl1Parser("S;J;X;F;G#c;i;p;s;z#S/c,sJ;J/pScX,i,e;X/zGiG,iGs,e;F/p,cGGi;G/pFF,sG#S/c,s;J/p,i,e;X/z,i,e;F/p,c;G/p,s#S/$c;J/$c;X/$c;F/cp;G/ips");
		assertEquals("S;sJ;spScX;spccX;spcciGs;spccipFFs;spccippFs;spccippps", cfgLl1Parser.parse("spccippps"));
	}

	@Test
	public void testCfg1String4() {
		CfgLl1Parser cfgLl1Parser= new CfgLl1Parser("S;J;X;F;G#c;i;p;s;z#S/c,sJ;J/pScX,i,e;X/zGiG,iGs,e;F/p,cGGi;G/pFF,sG#S/c,s;J/p,i,e;X/z,i,e;F/p,c;G/p,s#S/$c;J/$c;X/$c;F/cp;G/ips");
		assertEquals("S;sJ;spScX;spsJcX;spspScXcX;spspsJcXcX;spspspScXcXcX;ERROR", cfgLl1Parser.parse("spspsppc"));
	}

	@Test
	public void testCfg1String5() {
		CfgLl1Parser cfgLl1Parser= new CfgLl1Parser("S;J;X;F;G#c;i;p;s;z#S/c,sJ;J/pScX,i,e;X/zGiG,iGs,e;F/p,cGGi;G/pFF,sG#S/c,s;J/p,i,e;X/z,i,e;F/p,c;G/p,s#S/$c;J/$c;X/$c;F/cp;G/ips");
		assertEquals("S;sJ;spScX;spsJcX;spspScXcX;spspsJcXcX;spspscXcX;spspsccX;spspscc", cfgLl1Parser.parse("spspscc"));
	}

	@Test
	public void testCfg2String1() {
		CfgLl1Parser cfgLl1Parser= new CfgLl1Parser("S;G;M;C;N;O#c;g;k;n;p;v;y#S/gCg,y;G/cSO,vG,e;M/y,k,pC,e;C/g,c,kNc;N/y,vGnG,e;O/gOyG,pSMg#S/g,y;G/c,v,e;M/y,k,p,e;C/g,c,k;N/y,v,e;O/g,p#S/$gkpy;G/ny;M/g;C/g;N/c;O/ny");
		assertEquals("S;gCg;gkNcg;gkvGnGcg;gkvnGcg;gkvnvGcg;gkvnvcg;ERROR", cfgLl1Parser.parse("gkvnvynn"));
	}

	@Test
	public void testCfg2String2() {
		CfgLl1Parser cfgLl1Parser= new CfgLl1Parser("S;G;M;C;N;O#c;g;k;n;p;v;y#S/gCg,y;G/cSO,vG,e;M/y,k,pC,e;C/g,c,kNc;N/y,vGnG,e;O/gOyG,pSMg#S/g,y;G/c,v,e;M/y,k,p,e;C/g,c,k;N/y,v,e;O/g,p#S/$gkpy;G/ny;M/g;C/g;N/c;O/ny");
		assertEquals("S;gCg;gkNcg;gkvGnGcg;gkvcSOnGcg;gkvcgCgOnGcg;gkvcgkNcgOnGcg;gkvcgkcgOnGcg;ERROR", cfgLl1Parser.parse("gkvcgkcg"));
	}

	@Test
	public void testCfg2String3() {
		CfgLl1Parser cfgLl1Parser= new CfgLl1Parser("S;G;M;C;N;O#c;g;k;n;p;v;y#S/gCg,y;G/cSO,vG,e;M/y,k,pC,e;C/g,c,kNc;N/y,vGnG,e;O/gOyG,pSMg#S/g,y;G/c,v,e;M/y,k,p,e;C/g,c,k;N/y,v,e;O/g,p#S/$gkpy;G/ny;M/g;C/g;N/c;O/ny");
		assertEquals("S;gCg;gkNcg;gkvGnGcg;gkvvGnGcg;gkvvnGcg;gkvvncSOcg;gkvvncgCgOcg;ERROR", cfgLl1Parser.parse("gkvvncg"));
	}

	@Test
	public void testCfg2String4() {
		CfgLl1Parser cfgLl1Parser= new CfgLl1Parser("S;G;M;C;N;O#c;g;k;n;p;v;y#S/gCg,y;G/cSO,vG,e;M/y,k,pC,e;C/g,c,kNc;N/y,vGnG,e;O/gOyG,pSMg#S/g,y;G/c,v,e;M/y,k,p,e;C/g,c,k;N/y,v,e;O/g,p#S/$gkpy;G/ny;M/g;C/g;N/c;O/ny");
		assertEquals("S;gCg;gkNcg;gkvGnGcg;gkvvGnGcg;gkvvvGnGcg;gkvvvnGcg;gkvvvnvGcg;gkvvvnvvGcg;gkvvvnvvcSOcg;gkvvvnvvcgCgOcg;ERROR", cfgLl1Parser.parse("gkvvvnvvcg"));
	}

	@Test
	public void testCfg2String5() {
		CfgLl1Parser cfgLl1Parser= new CfgLl1Parser("S;G;M;C;N;O#c;g;k;n;p;v;y#S/gCg,y;G/cSO,vG,e;M/y,k,pC,e;C/g,c,kNc;N/y,vGnG,e;O/gOyG,pSMg#S/g,y;G/c,v,e;M/y,k,p,e;C/g,c,k;N/y,v,e;O/g,p#S/$gkpy;G/ny;M/g;C/g;N/c;O/ny");
		assertEquals("S;gCg;gkNcg;gkvGnGcg;gkvvGnGcg;gkvvvGnGcg;gkvvvnGcg;gkvvvncSOcg;gkvvvncgCgOcg;ERROR", cfgLl1Parser.parse("gkvvvncg"));
	}

}