/*
 * CDDL HEADER START
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License, Version 1.0 only
 * (the "License").  You may not use this file except in compliance
 * with the License.
 *
 * You can obtain a copy of the license at
 * trunk/opends/resource/legal-notices/OpenDS.LICENSE
 * or https://OpenDS.dev.java.net/OpenDS.LICENSE.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each
 * file and include the License file at
 * trunk/opends/resource/legal-notices/OpenDS.LICENSE.  If applicable,
 * add the following below this CDDL HEADER, with the fields enclosed
 * by brackets "[]" replaced with your own identifying information:
 *      Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 *
 *
 *      Copyright 2006-2008 Sun Microsystems, Inc.
 */
package org.opends.server.tools.makeldif;
import org.opends.messages.Message;



import java.util.List;

import org.opends.server.types.InitializationException;

import static org.opends.messages.ToolMessages.*;



/**
 * This class defines a tag that is used to hold static text (i.e., text that
 * appears outside of any tag).
 */
public class StaticTextTag
       extends Tag
{
  // The static text to include in the LDIF.
  private String text;



  /**
   * Creates a new instance of this static text tag.
   */
  public StaticTextTag()
  {
    text = "";
  }



  /**
   * Retrieves the name for this tag.
   *
   * @return  The name for this tag.
   */
  public String getName()
  {
    return "StaticText";
  }



  /**
   * Indicates whether this tag is allowed for use in the extra lines for
   * branches.
   *
   * @return  <CODE>true</CODE> if this tag may be used in branch definitions,
   *          or <CODE>false</CODE> if not.
   */
  public boolean allowedInBranch()
  {
    return true;
  }



  /**
   * Performs any initialization for this tag that may be needed while parsing
   * a branch definition.
   *
   * @param  templateFile  The template file in which this tag is used.
   * @param  branch        The branch in which this tag is used.
   * @param  arguments     The set of arguments provided for this tag.
   * @param  lineNumber    The line number on which this tag appears in the
   *                       template file.
   * @param  warnings      A list into which any appropriate warning messages
   *                       may be placed.
   *
   * @throws  InitializationException  If a problem occurs while initializing
   *                                   this tag.
   */
  public void initializeForBranch(TemplateFile templateFile, Branch branch,
                                  String[] arguments, int lineNumber,
                                  List<Message> warnings)
         throws InitializationException
  {
    if (arguments.length != 1)
    {
      Message message = ERR_MAKELDIF_TAG_INVALID_ARGUMENT_COUNT.get(
          getName(), lineNumber, 1, arguments.length);
      throw new InitializationException(message);
    }

    text = arguments[0];
  }



  /**
   * Performs any initialization for this tag that may be needed while parsing
   * a template definition.
   *
   * @param  templateFile  The template file in which this tag is used.
   * @param  template      The template in which this tag is used.
   * @param  arguments     The set of arguments provided for this tag.
   * @param  lineNumber    The line number on which this tag appears in the
   *                       template file.
   * @param  warnings      A list into which any appropriate warning messages
   *                       may be placed.
   *
   * @throws  InitializationException  If a problem occurs while initializing
   *                                   this tag.
   */
  public void initializeForTemplate(TemplateFile templateFile,
                                    Template template, String[] arguments,
                                    int lineNumber, List<Message> warnings)
         throws InitializationException
  {
    if (arguments.length != 1)
    {
      Message message = ERR_MAKELDIF_TAG_INVALID_ARGUMENT_COUNT.get(
          getName(), lineNumber, 1, arguments.length);
      throw new InitializationException(message);
    }

    text = arguments[0];
  }



  /**
   * Generates the content for this tag by appending it to the provided tag.
   *
   * @param  templateEntry  The entry for which this tag is being generated.
   * @param  templateValue  The template value to which the generated content
   *                        should be appended.
   *
   * @return  The result of generating content for this tag.
   */
  public TagResult generateValue(TemplateEntry templateEntry,
                                 TemplateValue templateValue)
  {
    templateValue.append(text);
    return TagResult.SUCCESS_RESULT;
  }
}

