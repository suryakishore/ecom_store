// Copyright 2018 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.app.ecomstore.uiutil.barcodescanning.barcode;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.app.ecomstore.uiutil.barcodescanning.graphic.FrameMetadata;
import com.app.ecomstore.uiutil.barcodescanning.graphic.GraphicOverlay;
import com.app.ecomstore.uiutil.barcodescanning.graphic.VisionProcessorBase;
import com.app.ecomstore.util.EcomUtil;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import java.io.IOException;
import java.util.List;

/** Barcode Detector Demo. */
public class BarcodeScanningProcessor extends VisionProcessorBase<List<FirebaseVisionBarcode>> {

  private static final String TAG = "BarcodeScanProc";
  private BarCodeCommunicator mCommunicator;

  private final FirebaseVisionBarcodeDetector detector;

  public BarcodeScanningProcessor(BarCodeCommunicator communicator) {
    detector = FirebaseVision.getInstance().getVisionBarcodeDetector();
    this.mCommunicator = communicator;
  }

  @Override
  public void stop() {
    try {
      detector.close();
    } catch (IOException e) {
      EcomUtil.printLog(TAG + "Exception thrown while trying to close Barcode Detector: " + e);
    }
  }

  @Override
  protected Task<List<FirebaseVisionBarcode>> detectInImage(FirebaseVisionImage image) {
    return detector.detectInImage(image);
  }

  @Override
  protected void onSuccess(
      @Nullable Bitmap originalCameraImage,
      @NonNull List<FirebaseVisionBarcode> barcodes,
      @NonNull FrameMetadata frameMetadata,
      @NonNull GraphicOverlay graphicOverlay) {
    graphicOverlay.clear();
    if (originalCameraImage != null) {
      CameraImageGraphic imageGraphic = new CameraImageGraphic(graphicOverlay, originalCameraImage);
      graphicOverlay.add(imageGraphic);
    }
    for (int i = 0; i < barcodes.size(); ++i) {
      FirebaseVisionBarcode barcode = barcodes.get(i);
      BarcodeGraphic barcodeGraphic = new BarcodeGraphic(graphicOverlay, barcode);
      graphicOverlay.add(barcodeGraphic);
    }
    String barCode = null;
    if (barcodes != null && barcodes.size() > 0) {
      barCode = barcodes.get(0).getRawValue();
    }
    mCommunicator.onSuccessFullBarCodeRead(barCode);
    graphicOverlay.postInvalidate();
  }

  @Override
  protected void onFailure(@NonNull Exception e) {
    EcomUtil.printLog(TAG + "Barcode detection failed " + e);
  }
}
