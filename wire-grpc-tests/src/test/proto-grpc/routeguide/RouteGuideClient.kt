// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: routeguide/RouteGuideProto.proto
package routeguide

import com.squareup.wire.GrpcCall
import com.squareup.wire.GrpcStreamingCall
import com.squareup.wire.Service
import com.squareup.wire.WireRpc

interface RouteGuideClient : Service {
  @WireRpc(
    path = "/routeguide.RouteGuide/GetFeature",
    requestAdapter = "routeguide.Point#ADAPTER",
    responseAdapter = "routeguide.Feature#ADAPTER"
  )
  fun GetFeature(): GrpcCall<Point, Feature>

  @WireRpc(
    path = "/routeguide.RouteGuide/ListFeatures",
    requestAdapter = "routeguide.Rectangle#ADAPTER",
    responseAdapter = "routeguide.Feature#ADAPTER"
  )
  fun ListFeatures(): GrpcStreamingCall<Rectangle, Feature>

  @WireRpc(
    path = "/routeguide.RouteGuide/RecordRoute",
    requestAdapter = "routeguide.Point#ADAPTER",
    responseAdapter = "routeguide.RouteSummary#ADAPTER"
  )
  fun RecordRoute(): GrpcStreamingCall<Point, RouteSummary>

  @WireRpc(
    path = "/routeguide.RouteGuide/RouteChat",
    requestAdapter = "routeguide.RouteNote#ADAPTER",
    responseAdapter = "routeguide.RouteNote#ADAPTER"
  )
  fun RouteChat(): GrpcStreamingCall<RouteNote, RouteNote>
}
