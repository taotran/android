package tvtran.com.vn.service;

import tvtran.com.vn.constant.InsuranceMaxRange;
import tvtran.com.vn.entity.Detail;

import java.util.List;
import java.util.Map;

import static tvtran.com.vn.utils.Utils.formattedDouble;
import static tvtran.com.vn.utils.Utils.writeContentToDetailList;

/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  6/28/2017
 */
public abstract class AbstractCalculator implements ICalculator
{

  //@formatter:off
  private static final double BHXH                        = 0.08;
  private static final double BHYT                        = 0.015;
  private static final double BHTN                        = 0.01;

  @Override
  public Double calcInsurancesSubtraction(Double salary)
  {
    final double appliedBHXH = salary <= InsuranceMaxRange.MAX_BHXH_BHYT_RANGE  ? salary * BHXH : InsuranceMaxRange.DEFAULT_OVER_RANGE_BHXH;
    final double appliedBHYT = salary <= InsuranceMaxRange.MAX_BHXH_BHYT_RANGE  ? salary * BHYT : InsuranceMaxRange.DEFAULT_OVER_RANGE_BHYT;
    final double appliedBHTN = salary <= InsuranceMaxRange.MAX_BHTN_AREA1_RANGE ? salary * BHTN : InsuranceMaxRange.DEFAULT_OVER_RANGE_BHTN;

    writeContentToDetailList(getDetailsList(), 1, formattedDouble(appliedBHXH));
    writeContentToDetailList(getDetailsList(), 2, formattedDouble(appliedBHYT));
    writeContentToDetailList(getDetailsList(), 3, formattedDouble(appliedBHTN));
    return salary - (appliedBHXH + appliedBHYT + appliedBHTN);
  }

  @Override
  public Double calcThueTNCNByRange(Double salaryAfterDependenciesSubtraction)
  {
    return null;
  }

  protected abstract List<Detail> getDetailsList();

//  protected abstract List<Detail> getDetailsTNCNList();

//  protected abstract List<Detail> getEmployerDetailsList();

}
