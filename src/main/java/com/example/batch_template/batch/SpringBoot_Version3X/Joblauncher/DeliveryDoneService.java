package com.example.batch_template.batch.SpringBoot_Version3X.Joblauncher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeliveryDoneService {

//    @Autowired
//    private DeliveryDao deliveryDao;
//
//    @Autowired
//    private DeliveryTraceDao deliveryTraceDao;
//
//    @Autowired
//    private ChannelSalesDeliveryDao channelSalesDeliveryDao;
//
//    @Autowired
//    private SingleCopyDeliveryDao singleCopyDeliveryDao;
//
//    @Autowired
//    private EtcOrderDeliveryDao etcOrderDeliveryDao;
//    @Autowired
//    private DeliveryInvoceDao deliveryInvoceDao;

    @Transactional
    public void deliveryDoneProc() throws Exception {
        this.orderDoneProcess(); // 일반주문
        this.singleCopyOrderDoneProcess(); // 낱권주문
        this.channelSalesDoneProcess(); // 채널매출
        this.etcOrderDoneProcess(); // 기타주문
    }

    /*dfs*/
    private void orderDoneProcess() throws Exception { // 일반주문 배송완료처리 로직

//        DeliveryRequest requestDto = new DeliveryRequest();
//        requestDto.setDeliveryRequestStatus(DeliveryConst.DELIVERY_REQUEST_STATUS_SCM_DELIVERY);
//        List<DeliveryRequest> scmDeliveryList = deliveryDao.selectDeliveryRequest(requestDto); // 출고상태인 배송요청 전체
//
//        List<String> invoiceNumberList = new ArrayList<>(); //DELIVERY_TRACE테이블 조회에 사용할 운송장번호리스트
//        for (DeliveryRequest request : scmDeliveryList) {
//            invoiceNumberList.add(request.getInvoiceNumber());
//        }
//
//        List<DeliveryTrace> traceList = new ArrayList<>();
//        if (invoiceNumberList.size() > 0) {
//            traceList = deliveryTraceDao.selectDoneList(invoiceNumberList);
//        }
//
//        if (traceList.size() > 0) {
//            Map<Integer, String> orderIdList = new HashMap<>();
//            for (DeliveryRequest request : scmDeliveryList) {
//                //update시 사용할 배송완료시간을 가져오기위해 for문은 2번 사용했음
//                for (DeliveryTrace trace : traceList) {
//                    if (trace.getInvcNo().equals(request.getInvoiceNumber())) {
//                        //배송요청테이블 업데이트 [ 항목 : delivery_request_status(배송요청상태) ,delivery_done_date(물류배송 완료일), last_updated_datetime(최종수정일시) ]
//                        request.setDeliveryDoneDatetime(trace.getRegDtime());
//                        deliveryDao.updateDeliveryRequestDone(request);
//                        this.insertLog(trace.getInvcNo(), TableNameType.DELIVERY_REQUEST.name());
//
//                        orderIdList.put(request.getOrderId(), orderIdList.get(request.getOrderId()) != null && LocalDateTime.parse(orderIdList.get(request.getOrderId()), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).isAfter(LocalDateTime.parse(trace.getRegDtime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))) ? orderIdList.get(request.getOrderId()) : trace.getRegDtime());
//                    }
//                }
//            }
//
//            for (Integer orderId : orderIdList.keySet()) {
//                Order order = new Order();
//                order.setOrderId(orderId);
//                order.setDeliveryDoneDate(orderIdList.get(orderId));
//                String salesDoneDate = deliveryDao.selectSalesDoneDate(order);
//                //Boolean done = deliveryDao.selectAllRequestIsDone(orderId); // 주문에 속해있는 배송요청들이 전부 배송완료상태인지 확인
//                if (salesDoneDate != null) {
//                    // 주문 테이블 업데이트 [ 항목 : order_status(가상계좌상태), delivery_done_date(배송완료일), last_updated_datetime(최종변경일시)
//                    Order orderDto = new Order();
//                    orderDto.setOrderId(orderId);
//                    orderDto.setDeliveryDoneDate(orderIdList.get(orderId));
//                    orderDto.setSalesDoneDate(salesDoneDate);
//                    deliveryDao.updateOrderDone(orderDto);
//                }
//            }
//        }
    }

    private void singleCopyOrderDoneProcess() throws Exception { // 낱권주문 배송완료처리 로직
//        SingleCopyDelivery deliveryDto = new SingleCopyDelivery();
//        deliveryDto.setSingleCopyDeliveryStatus(DeliveryConst.DELIVERY_REQUEST_STATUS_SCM_DELIVERY);
//        List<SingleCopyDelivery> scmDeliveryList = singleCopyDeliveryDao.selectDeliveryList(deliveryDto); // 출고상태인 낱권 배송요청 전체
//
//        List<String> invoiceNumberList = new ArrayList<>(); //DELIVERY_TRACE테이블 조회에 사용할 운송장번호리스트
//        for (SingleCopyDelivery delivery : scmDeliveryList) {
//            invoiceNumberList.add(delivery.getInvoiceNumber());
//        }
//
//        List<DeliveryTrace> traceList = new ArrayList<>();
//        if (invoiceNumberList.size() > 0) {
//            traceList = deliveryTraceDao.selectDoneList(invoiceNumberList);
//        }
//
//        Map<Integer, String> singleCopyOrderIdList = new HashMap<>();
//        for (SingleCopyDelivery delivery : scmDeliveryList) {
//            for (DeliveryTrace trace : traceList) {
//                if (trace.getInvcNo().equals(delivery.getInvoiceNumber())) {
//                    //배송요청테이블 업데이트 [ 항목 : single_copy_delivery_status(배송상태) ,delivery_done_date(물류배송 완료일), last_updated_datetime(최종수정일시) ]
//                    delivery.setDeliveryDoneDate(trace.getRegDtime());
//                    singleCopyDeliveryDao.updateSingleCopyDeliveryDone(delivery);
//                    this.insertLog(trace.getInvcNo(), TableNameType.SINGLE_COPY_DELIVERY.name());
//                    singleCopyOrderIdList.put(delivery.getSingleCopyOrderId(), singleCopyOrderIdList.get(delivery.getSingleCopyOrderId()) != null && LocalDateTime.parse(singleCopyOrderIdList.get(delivery.getSingleCopyOrderId()), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).isAfter(LocalDateTime.parse(trace.getRegDtime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))) ? singleCopyOrderIdList.get(delivery.getSingleCopyOrderId()) : trace.getRegDtime());
//                }
//            }
//        }
//
//        for (Integer singleCopyOrderId : singleCopyOrderIdList.keySet()) {
//            Boolean done = singleCopyDeliveryDao.selectAllRequestIsDone(singleCopyOrderId); // 주문에 속해있는 배송요청들이 전부 배송완료상태인지 확인
//            if (done) {
//                // 낱권주문 테이블 업데이트 [ 항목 : single_copy_order_status(낱권주문상태), delivery_done_date(배송완료일), last_updated_datetime(최종변경일시)
//                SingleCopyOrder singleCopyOrder = new SingleCopyOrder();
//                singleCopyOrder.setSingleCopyOrderId(singleCopyOrderId);
//                singleCopyOrder.setDeliveryDoneDate(singleCopyOrderIdList.get(singleCopyOrderId));
//                singleCopyDeliveryDao.updateSingleCopyOrderDone(singleCopyOrder);
//            }
//        }
    }

    private void channelSalesDoneProcess() throws Exception { // 채널주문 배송완료처리 로직
//        ChannelSalesDeliveryRequest request = new ChannelSalesDeliveryRequest();
//        request.setDeliveryRequestStatus(DeliveryConst.DELIVERY_REQUEST_STATUS_SCM_DELIVERY);
//        List<ChannelSalesDeliveryRequest> scmDeliveryList = channelSalesDeliveryDao.selectRequestList(request); // 출고상태인 채널매출 배송요청 전체
//
//        List<String> invoiceNumberList = new ArrayList<>(); //DELIVERY_TRACE테이블 조회에 사용할 운송장번호리스트
//        for (ChannelSalesDeliveryRequest channelSalesDeliveryRequest : scmDeliveryList) {
//            invoiceNumberList.add(channelSalesDeliveryRequest.getInvoiceNumber());
//        }
//
//        List<DeliveryTrace> traceList = new ArrayList<>();
//        if (invoiceNumberList.size() > 0) {
//            traceList = deliveryTraceDao.selectDoneList(invoiceNumberList);
//        }
//
//        for (ChannelSalesDeliveryRequest channelSalesDeliveryRequest : scmDeliveryList) {
//            for (DeliveryTrace trace : traceList) {
//                if (trace.getInvcNo().equals(channelSalesDeliveryRequest.getInvoiceNumber())) {
//                    // 채널매출배송요청테이블 업데이트 [ 항목 : delivery_request_status(배송요청상태) ,delivery_done_date(배송 완료일), last_updated_datetime(최종수정일시) ]
//                    channelSalesDeliveryRequest.setDeliveryDoneDate(trace.getRegDtime());
//                    channelSalesDeliveryDao.updateChannelSalesDeliveryRequestDone(channelSalesDeliveryRequest);
//                    this.insertLog(trace.getInvcNo(), TableNameType.CHANNEL_SALES_DELIVERY_REQUEST.name());
//                }
//            }
//        }
    }

    private void etcOrderDoneProcess() throws Exception { // 기타주문 배송완료처리 로직
//        EtcOrderDeliveryRequest request = new EtcOrderDeliveryRequest();
//        request.setDeliveryRequestStatus(DeliveryConst.DELIVERY_REQUEST_STATUS_SCM_DELIVERY);
//        List<EtcOrderDeliveryRequest> scmDeliveryList = etcOrderDeliveryDao.selectRequestList(request); // 출고상태인 기타 배송요청 전체
//
//        List<String> invoiceNumberList = new ArrayList<>(); //DELIVERY_TRACE테이블 조회에 사용할 운송장번호리스트
//        for (EtcOrderDeliveryRequest etcOrderDeliveryRequest : scmDeliveryList) {
//            invoiceNumberList.add(etcOrderDeliveryRequest.getInvoiceNumber());
//        }
//
//        List<DeliveryTrace> traceList = new ArrayList<>();
//        if (invoiceNumberList.size() > 0) {
//            traceList = deliveryTraceDao.selectDoneList(invoiceNumberList);
//        }
//
//        Map<Integer, String> etcOrderIdList = new HashMap<>();
//        for (EtcOrderDeliveryRequest deliveryRequest : scmDeliveryList) {
//            for (DeliveryTrace trace : traceList) {
//                if (trace.getInvcNo().equals(deliveryRequest.getInvoiceNumber())) {
//                    // 배송요청테이블 업데이트 [ 항목 : delivery_request_status(출고요청상태) ,delivery_done_datetime(배송완료일), last_updated_datetime(최종변경시간) ]
//                    deliveryRequest.setDeliveryDoneDatetime(trace.getRegDtime());
//                    etcOrderDeliveryDao.updateEtcOrderDeliveryRequestDone(deliveryRequest);
//                    this.insertLog(trace.getInvcNo(), TableNameType.ETC_ORDER_DELIVERY_REQUEST.name());
//                    etcOrderIdList.put(deliveryRequest.getEtcOrderId(), etcOrderIdList.get(deliveryRequest.getEtcOrderId()) != null && LocalDateTime.parse(etcOrderIdList.get(deliveryRequest.getEtcOrderId()), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).isAfter(LocalDateTime.parse(trace.getRegDtime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))) ? etcOrderIdList.get(deliveryRequest.getEtcOrderId()) : trace.getRegDtime());
//                }
//            }
//        }
//
//        for (Integer etcOrderId : etcOrderIdList.keySet()) {
//            Boolean done = etcOrderDeliveryDao.selectAllRequestIsDone(etcOrderId); // 주문에 속해있는 배송요청들이 전부 배송완료상태인지 확인
//            if (done) {
//                // 기타주문 테이블 업데이트 [ 항목 : etc_order_status(주문상태), delivery_done_date(배송완료일), last_updated_datetime(최종변경일시)
//                EtcOrder etcOrderDto = new EtcOrder();
//                etcOrderDto.setEtcOrderId(etcOrderId);
//                etcOrderDto.setDeliveryDoneDate(etcOrderIdList.get(etcOrderId));
//                etcOrderDeliveryDao.updateEtcOrderDone(etcOrderDto);
//            }
//
//        }
    }

    // 배송완료로그insert
    private void insertLog(String invoiceNumber, String tableName) throws Exception {
//        BatchInvoce batchInvoce = new BatchInvoce();
//        batchInvoce.setInvoiceNumber(invoiceNumber);
//        batchInvoce.setTableName(tableName);
//        batchInvoceDao.insertBatchInvoce(batchInvoce);
    }
}
