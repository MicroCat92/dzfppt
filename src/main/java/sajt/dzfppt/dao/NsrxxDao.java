package sajt.dzfppt.dao;


import sajt.dzfppt.entity.Nsrxx;

public interface NsrxxDao {

    Nsrxx selectByNsrsbh(String nsrsbh);
    
    Nsrxx selectAllByNsrxbh(String nsrsbh);
    
}