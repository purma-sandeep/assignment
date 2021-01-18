package com.provigil.model;

public class SurveillanceCostPlan {
    public static final int SQUARE_FOOTAGE_SLAB_1 = 2500;
    public static final int SQUARE_FOOTAGE_SLAB_2 = 5000;
    public static final int SQUARE_FOOTAGE_SLAB_3 = 50000;

    public static final double SLAB_1_MONTHLY_PLAN_FEE_PER_SQUARE_FEET_INDOOR = 2;
    public static final double SLAB_1_MONTHLY_PLAN_FEE_PER_SQUARE_FEET_OUTDOOR = 2.5;

    public static final double SLAB_2_MONTHLY_PLAN_FEE_PER_SQUARE_FEET = 1.5;

    public static final double SLAB_3_MONTHLY_PLAN_FEE_PER_SQUARE_FEET_INDOOR = 1;
    public static final double SLAB_3_MONTHLY_PLAN_FEE_PER_SQUARE_FEET_OUTDOOR = 1.2;

    public static final double SLAB_4_MONTHLY_PLAN_FEE_PER_SQUARE_FEET = 0.8;

    public static final double SLAB_1_YEARLY_PLAN_FEE_PER_SQUARE_FEET_INDOOR = 1.5;
    public static final double SLAB_1_YEARLY_PLAN_FEE_PER_SQUARE_FEET_OUTDOOR = 2;

    public static final double SLAB_2_YEARLY_PLAN_FEE_PER_SQUARE_FEET = 1;

    public static final double SLAB_3_YEARLY_PLAN_FEE_PER_SQUARE_FEET_INDOOR = 0.6;
    public static final double SLAB_3_YEARLY_PLAN_FEE_PER_SQUARE_FEET_OUTDOOR = 0.8;

    public static final double SLAB_4_YEARLY_PLAN_FEE_PER_SQUARE_FEET = 0.5;


    public enum AreaType {
        INDOOR,
        OUTDOOR
    }
    public enum PlanType {
        YEARLY,
        MONTHLY
    }

    long id;
    AreaType areaType = AreaType.INDOOR;
    PlanType planType = PlanType.MONTHLY;
    double cost;

    public double getCost(int areaInSqaureFeets, AreaType areaType, PlanType planType) {
        double cost = 0;
        if (areaInSqaureFeets <= SQUARE_FOOTAGE_SLAB_1) {
            if (areaType == AreaType.INDOOR) {
                cost = planType == PlanType.MONTHLY ? areaInSqaureFeets * SLAB_1_MONTHLY_PLAN_FEE_PER_SQUARE_FEET_INDOOR : areaInSqaureFeets * SLAB_1_YEARLY_PLAN_FEE_PER_SQUARE_FEET_INDOOR;
            } else {
                cost = planType == PlanType.MONTHLY ? areaInSqaureFeets * SLAB_1_MONTHLY_PLAN_FEE_PER_SQUARE_FEET_OUTDOOR : areaInSqaureFeets * SLAB_1_YEARLY_PLAN_FEE_PER_SQUARE_FEET_OUTDOOR;
            }
        } else if (areaInSqaureFeets <= SQUARE_FOOTAGE_SLAB_2) {
            int slabArea = areaInSqaureFeets - SQUARE_FOOTAGE_SLAB_1;
            cost = getCostForFullSlab(areaType, planType, SQUARE_FOOTAGE_SLAB_1);
            cost += planType == PlanType.MONTHLY ? slabArea * SLAB_2_MONTHLY_PLAN_FEE_PER_SQUARE_FEET : slabArea * SLAB_2_YEARLY_PLAN_FEE_PER_SQUARE_FEET;
        } else if (areaInSqaureFeets <= SQUARE_FOOTAGE_SLAB_3) {
            int slabArea = areaInSqaureFeets - SQUARE_FOOTAGE_SLAB_2;
            cost = getCostForFullSlab(areaType, planType, SQUARE_FOOTAGE_SLAB_1);
            cost += getCostForFullSlab(areaType, planType, SQUARE_FOOTAGE_SLAB_2);
            if (areaType == AreaType.INDOOR) {
                cost += planType == PlanType.MONTHLY ? slabArea * SLAB_3_MONTHLY_PLAN_FEE_PER_SQUARE_FEET_INDOOR : slabArea * SLAB_3_YEARLY_PLAN_FEE_PER_SQUARE_FEET_INDOOR;
            } else {
                cost += planType == PlanType.MONTHLY ? slabArea * SLAB_3_MONTHLY_PLAN_FEE_PER_SQUARE_FEET_OUTDOOR : slabArea * SLAB_3_YEARLY_PLAN_FEE_PER_SQUARE_FEET_OUTDOOR;
            }
        } else {
            int slabArea = areaInSqaureFeets - SQUARE_FOOTAGE_SLAB_3;
            cost = getCostForFullSlab(areaType, planType, SQUARE_FOOTAGE_SLAB_1);
            cost += getCostForFullSlab(areaType, planType, SQUARE_FOOTAGE_SLAB_2);
            cost += getCostForFullSlab(areaType, planType, SQUARE_FOOTAGE_SLAB_3);
            cost += planType == PlanType.MONTHLY ? slabArea * SLAB_4_MONTHLY_PLAN_FEE_PER_SQUARE_FEET : slabArea * SLAB_4_YEARLY_PLAN_FEE_PER_SQUARE_FEET;
        }

        return cost;
    }

    public double getCostForFullSlab(AreaType areaType, PlanType planType, int slab) {
        double cost = 0;
        switch (slab) {
            case SQUARE_FOOTAGE_SLAB_1:
                {
                    if (areaType == AreaType.INDOOR) {
                        cost = planType == PlanType.MONTHLY ? SQUARE_FOOTAGE_SLAB_1 * SLAB_1_MONTHLY_PLAN_FEE_PER_SQUARE_FEET_INDOOR : SQUARE_FOOTAGE_SLAB_1 * SLAB_1_YEARLY_PLAN_FEE_PER_SQUARE_FEET_INDOOR;
                    } else {
                        cost = planType == PlanType.MONTHLY ? SQUARE_FOOTAGE_SLAB_1 * SLAB_1_MONTHLY_PLAN_FEE_PER_SQUARE_FEET_OUTDOOR : SQUARE_FOOTAGE_SLAB_1 * SLAB_1_YEARLY_PLAN_FEE_PER_SQUARE_FEET_OUTDOOR;
                    }
                    break;

                }
            case SQUARE_FOOTAGE_SLAB_2:
                {
                    int slabArea = (SQUARE_FOOTAGE_SLAB_2 - SQUARE_FOOTAGE_SLAB_1);
                    cost = planType == PlanType.MONTHLY ? slabArea * SLAB_2_MONTHLY_PLAN_FEE_PER_SQUARE_FEET : slabArea * SLAB_2_YEARLY_PLAN_FEE_PER_SQUARE_FEET;
                    break;

                }
            case SQUARE_FOOTAGE_SLAB_3:
                {
                    int slabArea = (SQUARE_FOOTAGE_SLAB_3 - SQUARE_FOOTAGE_SLAB_2);
                    if (areaType == AreaType.INDOOR) {
                        cost = planType == PlanType.MONTHLY ? slabArea * SLAB_3_MONTHLY_PLAN_FEE_PER_SQUARE_FEET_INDOOR : slabArea * SLAB_3_YEARLY_PLAN_FEE_PER_SQUARE_FEET_INDOOR;
                    } else {
                        cost = planType == PlanType.MONTHLY ? slabArea * SLAB_3_MONTHLY_PLAN_FEE_PER_SQUARE_FEET_OUTDOOR : slabArea * SLAB_3_YEARLY_PLAN_FEE_PER_SQUARE_FEET_OUTDOOR;
                    }
                    break;

                }
            default:
                break;
        }
        return cost;

    }
}