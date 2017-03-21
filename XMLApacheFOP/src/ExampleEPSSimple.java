/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/* $Id$ */

//package embedding;

import java.awt.Font;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.xmlgraphics.java2d.GraphicContext;
import org.apache.xmlgraphics.java2d.ps.EPSDocumentGraphics2D;

import org.apache.fop.render.ps.NativeTextHandler;

public class ExampleEPSSimple {

  /**
   * @param args
   */
  public static void main(String[] args) {
    try {
      OutputStream out = new FileOutputStream("example_eps_simple.eps");
      EPSDocumentGraphics2D g2d = new EPSDocumentGraphics2D(false);
      g2d.setGraphicContext(new GraphicContext());
      g2d.setCustomTextHandler(new NativeTextHandler(g2d, null));
      g2d.setupDocument(out, 200, 100);
      g2d.setFont(new Font("Helvetica", Font.PLAIN, 12));
      g2d.drawString("Hi there Helvetica", 50, 50);
      g2d.finish();
      out.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
