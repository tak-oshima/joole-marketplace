export interface AdvancedSearchRequest {
    productTypeSearchRequest: {
        application: string | null;
        type: string | null;
        mountingLocation: string | null;
        accessories: string | null;
        minModelYear: Date | null;   // yyyy-mm-dd
        maxModelYear: Date | null;   // yyyy-mm-dd
    };
    technicalDetailSearchRequest: {
        minAirflow: number;
        maxAirflow: number;
        minPower: number;
        maxPower: number;
        minOperatingVoltage: number;
        maxOperatingVoltage: number;
        minFanSpeed: number;
        maxFanSpeed: number;
    };
    productBrand: string;
}
