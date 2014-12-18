package pe.mil.ejercito.sipr.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.mil.ejercito.sipr.ejbremote.BoletaDetalleEjbRemote;
import pe.mil.ejercito.sipr.model.SipreBoletaDetalle;
import pe.mil.ejercito.sipr.model.SiprePlanillaDetalle;

@Stateless
public class BoletaDetalleEjbBean extends
		GenericDAOImpl<SipreBoletaDetalle> implements
		BoletaDetalleEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager	em;

	@Override
	public SipreBoletaDetalle saveDetalle(SipreBoletaDetalle boleta) {
		try{
			StringBuilder sb=new StringBuilder();
			
			sb.append("INSERT INTO Sipre_Boleta_Detalle ");
			sb.append(" (CBC_MES_PROCESO,CBC_NRO_ADM,NBC_NUM_PROCESO,");
			sb.append(" CBD_TIP_PLANILLA,NBD_SEC,CBD_TIP_CONCPTO, ");
			sb.append(" CBD_COD_ING_DESC,VBD_DSC_ING_DESC,NBD_MONTO, ");
			sb.append(" NBD_NUM_CUO_TOTAL,NBD_NUM_CUO_PAGADA, ");
			sb.append(" CBD_IND_SUBTITULO,CBD_COD_MEF ) ");
			sb.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) ");
			
			Query q=em.createNativeQuery(sb.toString());
			System.out.println("sub string 1::>"+sb.toString());
			System.out.println("sub string 2::>"+boleta.getSipreBoletaDetallePK().getCbcMesProceso());
			System.out.println("sub string 3::>"+boleta.getSipreBoletaDetallePK().getCbcNroAdm());
			System.out.println("sub string 4::>"+boleta.getSipreBoletaDetallePK().getNbcNumProceso());
			System.out.println("sub string 5::>"+boleta.getSipreBoletaDetallePK().getCbdTipPlanilla());
			System.out.println("sub string 6::>"+boleta.getSipreBoletaDetallePK().getNbdSec());
			System.out.println("sub string 7::>"+boleta.getCbdTipConcpto());
			System.out.println("sub string 8::>"+boleta.getCbdCodIngDesc());
			System.out.println("sub string 9::>"+boleta.getVbdDscIngDesc());
			System.out.println("sub string 10::>"+boleta.getNbdMonto());
			System.out.println("sub string 11::>"+boleta.getNbdNumCuoTotal());
			System.out.println("sub string 12::>"+boleta.getNbdNumCuoPagada());
			System.out.println("sub string 13::>"+boleta.getCbdIndSubtitulo());
			System.out.println("sub string 14::>"+boleta.getCbdCodMef());
			q.setParameter(1, boleta.getSipreBoletaDetallePK().getCbcMesProceso());
			q.setParameter(2, boleta.getSipreBoletaDetallePK().getCbcNroAdm());
			q.setParameter(3, boleta.getSipreBoletaDetallePK().getNbcNumProceso());
			q.setParameter(4, boleta.getSipreBoletaDetallePK().getCbdTipPlanilla());
			q.setParameter(5, boleta.getSipreBoletaDetallePK().getNbdSec());
			q.setParameter(6, boleta.getCbdTipConcpto());
			q.setParameter(7, boleta.getCbdCodIngDesc());
			q.setParameter(8, boleta.getVbdDscIngDesc());
			q.setParameter(9, boleta.getNbdMonto());
			q.setParameter(10,boleta.getNbdNumCuoTotal());
			q.setParameter(11,boleta.getNbdNumCuoPagada());
			q.setParameter(12,boleta.getCbdIndSubtitulo());
			q.setParameter(13, boleta.getCbdCodMef());
			q.executeUpdate();
			return boleta;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		// TODO Auto-generated method stub
		
	}

	

	
}
